package com.cskaoyan.service;

import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.MarketStorageExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.mapper.MarketStorageMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<MarketStorage> queryAllStorage(BaseParam baseParam) {
        MarketStorageExample example = new MarketStorageExample();
        example.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
        List<MarketStorage> marketStorages = marketStorageMapper.selectByExample(example);

        return marketStorages;
    }

    @Override
    public void updateStorageById(MarketStorage marketStorage) {
        marketStorageMapper.updateByPrimaryKey(marketStorage);
    }
}
