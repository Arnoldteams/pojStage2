package com.cskaoyan.service;

import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.MarketKeywordExample;
import com.cskaoyan.bean.bo.AdminKeywordBO;
import com.cskaoyan.mapper.MarketKeywordMapper;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:15:23
 * @version: v0.1
 * @Description:
 */
@Service
public class AdminKeywordServiceImpl implements AdminKeywordService {

    @Autowired
    MarketKeywordMapper marketKeywordMapper;

    @Override
    public List<MarketKeyword> queryAllKeywordList(AdminKeywordBO adminKeywordBO) {

        MarketKeywordExample example = new MarketKeywordExample();
        MarketKeywordExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(adminKeywordBO.getKeyword())){
            criteria.andKeywordLike("%" + adminKeywordBO.getKeyword() + "%");// 查找条件为关键词
        }
        if(!StringUtils.isEmpty(adminKeywordBO.getUrl())){
            criteria.andUrlLike("%"+adminKeywordBO.getUrl()+"%"); // 查找条件为关键词
        }

        criteria.andDeletedEqualTo(false); // 显示没有删除的数据

        example.setOrderByClause(adminKeywordBO.getSort()+" "+adminKeywordBO.getOrder());

        PageHelper.startPage(adminKeywordBO.getPage(), adminKeywordBO.getLimit());
        List<MarketKeyword> marketKeywords = marketKeywordMapper.selectByExample(example);

        return marketKeywords;
    }

    @Override
    public MarketKeyword insertKeyword(AdminKeywordBO adminKeywordBO) {
        MarketKeyword marketKeyword = new MarketKeyword();
        marketKeyword.setKeyword(adminKeywordBO.getKeyword());
        marketKeyword.setUrl(adminKeywordBO.getUrl());
        marketKeyword.setIsHot(adminKeywordBO.getIsHot());
        marketKeyword.setIsDefault(adminKeywordBO.getIsDefault());

        marketKeyword.setSortOrder(100);
        marketKeyword.setDeleted(false);

        marketKeywordMapper.insert(marketKeyword);
        return marketKeyword;
    }

    @Override
    public void updateKeywordById(MarketKeyword marketKeyword) {
        marketKeywordMapper.updateByPrimaryKey(marketKeyword);
    }

    @Override
    public void deleteKeywordById(MarketKeyword marketKeyword) {

        marketKeyword.setDeleted(true);

        marketKeywordMapper.updateByPrimaryKey(marketKeyword);
    }
}
