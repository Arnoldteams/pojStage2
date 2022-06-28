package com.cskaoyan.service;

import com.cskaoyan.bean.MarketSystem;
import com.cskaoyan.bean.MarketSystemExample;
import com.cskaoyan.bean.bo.AdminConfigBO;
import com.cskaoyan.bean.vo.AdminConfigVO;
import com.cskaoyan.mapper.MarketSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 10:09:22
 * @version:
 * @Description:
 */
@Service
public class AdminConfigServiceImpl implements AdminConfigService {

    @Autowired
    MarketSystemMapper marketSystemMapper;

    @Override
    public Object getMapByKey(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o = constructor.newInstance();

        Map<String, String> all = getAllSystemMap();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = all.get(name);
            field.set(o, value);
        }

        return o;
    }

    @Override
    public Map<String, String> getAllSystemMap() {

        MarketSystemExample example = new MarketSystemExample();
        MarketSystemExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false); // 显示没有删除的数据

        HashMap<String, String> hashMap = new HashMap<>();
        for (MarketSystem marketSystem : marketSystemMapper.selectByExample(example)) {
            hashMap.put(marketSystem.getKeyName(), marketSystem.getKeyValue());
        }

        return hashMap;
    }

    @Override
    public AdminConfigVO getInfo() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return (AdminConfigVO) getMapByKey(AdminConfigVO.class);
    }

    @Override
    public void updateInfo(AdminConfigBO adminConfigBO) throws IllegalAccessException {
        Map<String, String> map = ObjectToMap(adminConfigBO,AdminConfigBO.class);

        // key 是AdminConfigBO的一个成员变量名，对应system表中key_name的一个值
        for (String key : map.keySet()) {

            // 成员变量的值为空，对应的map-value也为空，无需更新
            if (map.get(key) == null){
                continue;
            }

            MarketSystemExample example = new MarketSystemExample();
            MarketSystemExample.Criteria or = example.or();
            or.andKeyNameEqualTo(key); // 列的值,等价于... where key_name = 'key'

            // 在system表中，根据列key_name查找，因为key_name值唯一，查到的list只有一个值
            List<MarketSystem> marketSystems = marketSystemMapper.selectByExample(example);

            // 拿到key_name对应的id
            Integer id = marketSystems.get(0).getId();

            // 创建MarketSystem对象，赋值 id，key_value
            MarketSystem marketSystem = new MarketSystem();
            marketSystem.setId(id);
            marketSystem.setKeyName(key);
            marketSystem.setKeyValue(map.get(key));

            // 更新
            marketSystemMapper.updateByPrimaryKey(marketSystem);
        }

    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 14:17:59
     * @description: 将对象的成员变量名 -> key ,成员变量值 -> value，转换为map
     * @param: o - [Object]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     */
    @Override
    public Map<String, String> ObjectToMap(Object o,Class clazz) throws IllegalAccessException {

        HashMap<String, String> hashMap = new HashMap<>();

        for (Field filed : clazz.getDeclaredFields()) {
            filed.setAccessible(true);
            String name = filed.getName();
            Object value = filed.get(o);
            hashMap.put(name,value.toString());
        }

        return hashMap;
    }
}
