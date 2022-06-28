package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketCoupon;
import com.cskaoyan.bean.bo.MarketCouponBO;
import com.cskaoyan.bean.param.CommonData;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-26 16:49:40
 * @version:
 * @Description:
 */
public interface AdminCouponService {
    CommonData getCouponList(String name , Short type , Short status ,BasePageInfo info);

    MarketCoupon changeCouponInfo(MarketCoupon marketCoupon);

    MarketCouponBO addCoupon(MarketCouponBO marketCouponBO);

    Boolean deleteCouponById(MarketCoupon marketCoupon);

    MarketCoupon getCouponById(Integer id);

    CommonData getCouponUserList(Integer couponId , Integer userId , Short status ,BasePageInfo info);
}
