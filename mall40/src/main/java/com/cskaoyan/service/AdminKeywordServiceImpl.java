package com.cskaoyan.service;

import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.MarketKeywordExample;
import com.cskaoyan.bean.bo.AdminKeywordBO;
import com.cskaoyan.mapper.MarketKeywordMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:15:23
 * @version:
 * @Description:
 */
@Service
public class AdminKeywordServiceImpl implements AdminKeywordService {

    @Autowired
    MarketKeywordMapper marketKeywordMapper;

    @Override
    public List<MarketKeyword> queryAllKeywordList(AdminKeywordBO adminKeywordBO) {

        MarketKeywordExample example = new MarketKeywordExample();
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

        marketKeywordMapper.insert(marketKeyword);
        return marketKeyword;
    }
}
