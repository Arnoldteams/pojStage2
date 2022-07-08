package com.cskaoyan.service;

import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.param.CommonData;

public interface WxIssueService {
    CommonData<MarketIssue> queryAllIssue(Integer page, Integer limit);
}
