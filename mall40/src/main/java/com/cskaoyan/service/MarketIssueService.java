package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.param.CommonData;

/**
* @author:
* @createTime: 2022/06/26 14:47:25
* @description:商场首页-通用问题
        */
public interface MarketIssueService {
    CommonData<MarketIssue> queryMarketIssue(BasePageInfo basePageInfo,String question);

    MarketIssue addMarketIssue(String question, String answer);

    void deleteMarketIssue(MarketIssue marketIssue);

    MarketIssue updateMarketIssue(MarketIssue marketIssue);

    void updateMarketIssueStatus(MarketIssue marketIssue);
}
