package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.bo.wxAdressBo.WxAddressSaveBO;
import com.cskaoyan.bean.param.CommonData;


public interface WxAddressService {

    /***
     * @author: 文陶
     * @createTime: 2022/06/29 14:55:25
     * @description: wx/address/list
     * @param:  null
     * @return: java.util.List<com.cskaoyan.bean.MarketAddress>
     */
    CommonData<MarketAddress> queryAddressList();


    MarketAddress queryAddressById(Integer id);

    void deleteAddressById(Integer id);

    Integer save(WxAddressSaveBO address);
}
