package com.cskaoyan.service;

import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.MarketStorageExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.mapper.MarketStorageMapper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 16:07:35
 * @version:
 * @Description:
 */
@Service
public class AdminStorageServiceImpl implements AdminStorageService {

    @Autowired
    MarketStorageMapper marketStorageMapper;

    @Override
    public List<MarketStorage> queryAllStorage(BaseParam baseParam,String key,String name) {
        MarketStorageExample example = new MarketStorageExample();
        MarketStorageExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(key)){
            criteria.andKeyLike("%"+key+"%");
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }

        example.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        List<MarketStorage> marketStorages = marketStorageMapper.selectByExample(example);

        return marketStorages;
    }

    @Override
    public void updateStorageById(MarketStorage marketStorage) {
        marketStorageMapper.updateByPrimaryKey(marketStorage);
    }


    /**
     * @param marketStorage 图片对象
     * @author: Sssd
     */
    @Override
    public void addAdminStorage(MarketStorage marketStorage) {
        marketStorageMapper.insertStorage(marketStorage);
    }

    @Override
    public void deleteKeywordById(MarketStorage marketStorage) {
        marketStorageMapper.deleteByPrimaryKey(marketStorage.getId());

    }
}
