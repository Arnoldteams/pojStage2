package com.cskaoyan.service;

import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.bo.AdminKeywordBO;

import java.util.List;

@SuppressWarnings("all")
public interface AdminKeywordService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 09:04:19
     * @description: 根据分页信息查询keyword表的全部数据
     * @param: adminKeywordBO - [AdminKeywordBO]
     * @return: java.util.List<com.cskaoyan.bean.MarketKeyword>
     */
    List<MarketKeyword> queryAllKeywordList(AdminKeywordBO adminKeywordBO);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 09:06:56
     * @description: 向keyword表插入一条数据，并返回这条数据 - MarketKeyword
     * @param: adminKeywordBO - [AdminKeywordBO]
     * @return: com.cskaoyan.bean.MarketKeyword
     */
    MarketKeyword insertKeyword(AdminKeywordBO adminKeywordBO);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 09:08:51
     * @description: keyword表根据id更新一条数据，并绑定PO - MarketKeyword
     * @param: marketKeyword - [MarketKeyword]
     * @return: void
     */
    void updateKeywordById(MarketKeyword marketKeyword);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 09:17:09
     * @description: 根据id在Keyword表删除数据
     * @param: marketKeyword - [MarketKeyword]
     * @return: void
     */
    void deleteKeywordById(MarketKeyword marketKeyword);
}
