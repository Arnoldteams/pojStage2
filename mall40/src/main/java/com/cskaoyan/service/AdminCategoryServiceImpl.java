package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.mapper.MarketBrandMapper;
import com.cskaoyan.mapper.MarketCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/27 19:44
 */
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{
    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Override
    public List<MarketCategoryChildren> getAllCategory() {
        List<MarketCategoryChildren> list = marketCategoryMapper.selectL1CategoryByPid("L1");

        for (MarketCategoryChildren children : list) {
            Integer id = children.getId();
            List<MarketCategory> list1 = marketCategoryMapper.selectL2CategoryByLevelAndPid("L2",id);
            children.setChildren(list1);
        }
        return list;
    }

    @Override
    public List<AdminCategoryOne> getL1Category() {
        Integer pid = 0;
        List<AdminCategoryOne> list = marketCategoryMapper.selectL1Category(pid);

        return list;
    }

    @Override
    public MarketCategory createOneCategory(MarketCategory marketCategory) {
        Date date = new Date();
        marketCategory.setAddTime(date);
        marketCategory.setUpdateTime(date);
        marketCategoryMapper.insertSelective(marketCategory);
        return marketCategory;
    }

    @Override
    public void updateOneCategory(MarketCategory marketCategory) {
        marketCategoryMapper.updateByPrimaryKeySelective(marketCategory);
    }

    @Override
    public void deleteOneCategory(MarketCategory marketCategory) {
        marketCategoryMapper.deleteByPrimaryKey(marketCategory.getId());
    }


}
