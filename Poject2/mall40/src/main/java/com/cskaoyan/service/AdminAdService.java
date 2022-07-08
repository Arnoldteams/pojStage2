package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAd;
import com.cskaoyan.bean.param.CommonData;

public interface AdminAdService {
    CommonData getAdList(String name,String content,BasePageInfo info);

    MarketAd changeAd(MarketAd marketAd);

    MarketAd addAd(MarketAd marketAd);

    Boolean deleteAdById(MarketAd marketAd);
}
