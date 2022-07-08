package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketSearchHistory;
import com.cskaoyan.bean.param.CommonData;

public interface AdminHistoryService {
    /**
     * @author: 文陶
     * @createTime: 2022/06/26 21:46:16
     * @description:admin/history/list
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketSearchHistory>
     */
    CommonData<MarketSearchHistory> queryHistroy(Integer userId, String keyword, BasePageInfo pageInfo);
}
