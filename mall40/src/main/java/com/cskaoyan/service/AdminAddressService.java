package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.param.CommonData;

public interface AdminAddressService {
    /**
     * @author: 文陶
     * @createTime: 2022/06/26 17:55:15
     * @description:admin/address/list
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketAddress>
     */
    CommonData<MarketAddress> queryAddress(String name, Integer userId, BasePageInfo pageInfo);
}
