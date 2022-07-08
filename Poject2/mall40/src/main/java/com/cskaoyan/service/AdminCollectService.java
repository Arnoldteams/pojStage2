package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.param.CommonData;

public interface AdminCollectService {
    /**
     * @author: 文陶
     * @createTime: 2022/06/26 21:01:44
     * @description:admin/collect/list
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketCollect>
     */
    CommonData<MarketCollect> queryCollect(Integer userId, Integer valueId, BasePageInfo pageInfo);
}
