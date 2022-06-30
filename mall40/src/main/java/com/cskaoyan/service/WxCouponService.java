package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.param.CommonData;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-29 14:11:40
 * @version:
 * @Description:
 */
public interface WxCouponService {
    int getCouponForUser(String username, Integer couponId);

    CommonData getUserCouponList(String username, Integer status,Integer cartId, BasePageInfo info);

    int addCouponByCode(String code);

}
