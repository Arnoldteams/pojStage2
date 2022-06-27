package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketFootprint;
import com.cskaoyan.bean.param.CommonData;

public interface AdminFootprintService {
    CommonData<MarketFootprint> queryFootprint(Integer userId, Integer goodsId, BasePageInfo pageInfo);
}
