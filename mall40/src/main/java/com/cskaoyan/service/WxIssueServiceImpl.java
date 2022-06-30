package com.cskaoyan.service;

import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.MarketIssueExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketIssueMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/30 09:13
 */
@Service
public class WxIssueServiceImpl implements WxIssueService{

    @Autowired
    MarketIssueMapper marketIssueMapper;

    @Override
    public CommonData<MarketIssue> queryAllIssue(Integer page, Integer limit) {
        MarketIssueExample example = new MarketIssueExample();

        PageHelper.startPage(page,limit);
        List<MarketIssue> marketIssues = marketIssueMapper.selectByExample(example);

        PageInfo<MarketIssue> pageInfo = new PageInfo<>(marketIssues);

        return CommonData.data(pageInfo);
    }
    
}
