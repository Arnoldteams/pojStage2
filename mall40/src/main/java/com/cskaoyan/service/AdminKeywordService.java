package com.cskaoyan.service;

import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.bo.AdminKeywordBO;

import java.util.List;

public interface AdminKeywordService {
    List<MarketKeyword> queryAllKeywordList(AdminKeywordBO adminKeywordBO);

    MarketKeyword insertKeyword(AdminKeywordBO adminKeywordBO);

    void updateKeywordById(MarketKeyword marketKeyword);
}
