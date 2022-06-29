package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-28 20:58:35
 * @version:
 * @Description:优惠券
 */

@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {

    @Autowired
    AdminCouponService adminCouponService;


    /**
     * 在首页中展示全部优惠券信息
     *
     * @param info 分页信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData><com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/29 9:11
     */

    @RequestMapping("list")
    public BaseRespVo getAllList(BasePageInfo info){
        CommonData couponList = adminCouponService.getCouponList(null, null, null, info);
        return BaseRespVo.ok(couponList);
    }
}
