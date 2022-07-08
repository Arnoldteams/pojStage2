package com.cskaoyan.service;

import com.cskaoyan.bean.bo.AdminConfigBO;
import com.cskaoyan.bean.vo.AdminConfigVO;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public interface AdminConfigService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 10:38:56
     * @description: 根据类的成员变量名（key），在AllSystemMap中取到value，封装为对象
     * @param: clazz - [Class]
     * @return: java.lang.Object
     */
    Object getMapByKey(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 10:26:14
     * @description: 在Marker_System获取所有key-val
     * @param:
     * @return: java.util.Map<java.lang.String, java.lang.String>
     */
    Map<String, String> getAllSystemMap();

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 10:11:59
     * @description: 在Marker_System获取包含Mall的信息的字段，并封装
     * @param:
     * @return: com.cskaoyan.bean.vo.AdminConfigVO
     */
    AdminConfigVO getInfo() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 15:19:08
     * @description: 根据对象中的成员变量 -> key_name , 成员变量值 -> key_value 更新表
     * @param: adminConfigBO - [AdminConfigBO]
     * @return: void
     */
    void updateInfo(AdminConfigBO adminConfigBO) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 15:20:38
     * @description: 将对象的成员变量以key-value形式保存到map中
     * @param: o - [Object]
     * @param: clazz - [Class]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     */
    Map<String, String> ObjectToMap(Object o, Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
