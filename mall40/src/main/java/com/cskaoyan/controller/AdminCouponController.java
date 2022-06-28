package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketCoupon;
import com.cskaoyan.bean.bo.MarketCouponBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-26 16:46:26
 * @version:
 * @Description: 优惠券管理
 */
@RestController
@RequestMapping("admin/coupon")
public class AdminCouponController {

    @Autowired
    AdminCouponService adminCouponService;

    /**
     * 根据条件获得优惠券信息
     *
     * @param name 优惠券名称
     * @param type 优惠券类型
     * @param status 优惠券使用状态
     * @param info 分页信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData><com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 17:47
     */

    @RequestMapping("list")
    public BaseRespVo getCouponList(String name , Short type , Short status ,BasePageInfo info){
        CommonData data = adminCouponService.getCouponList(name,type,status,info);
        return BaseRespVo.ok(data);
    }

    /**
     * 根据条件更改优惠券信息
     *
     * @param marketCoupon 优惠券信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:23
     */


    @RequestMapping("update")
    public BaseRespVo changeCouponInfo(@RequestBody MarketCoupon marketCoupon){
        MarketCoupon updatedMarketCoupon = adminCouponService.changeCouponInfo(marketCoupon);
        return BaseRespVo.ok(updatedMarketCoupon);
    }


    /**
     * 根据条件新增优惠券信息
     *
     * @param marketCouponBO 优惠券信息(自创)
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:26
     */

    @RequestMapping("create")
    public BaseRespVo addCoupon(@RequestBody MarketCouponBO marketCouponBO){
        MarketCouponBO addedCouponBO = adminCouponService.addCoupon(marketCouponBO);
        return BaseRespVo.ok(addedCouponBO);
    }



    /**
     * 根据条件删除优惠券信息
     *
     * @param marketCoupon 优惠券信息
     * @return com.cskaoyan.bean.BaseRespVo 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:29
     */

    @PostMapping("delete")
    public BaseRespVo deleteCoupon(@RequestBody MarketCoupon marketCoupon){
        Boolean aBoolean = adminCouponService.deleteCouponById(marketCoupon);
        BaseRespVo<String> baseRespVo = new BaseRespVo<>();
        if(aBoolean){
            return baseRespVo;
        }
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }


    /**
     * 显示优惠券详情信息
     *
     * @param id 优惠券编号
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:31
     */

    @GetMapping("read")
    public BaseRespVo readCoupon(Integer id){
        MarketCoupon marketCoupon = adminCouponService.getCouponById(id);
        return BaseRespVo.ok(marketCoupon);
    }

    /**
     * 根据条件显示优惠券使用者信息
     *
     * @param couponId 优惠券编号
     * @param userId 使用者编号
     * @param status 优惠券使用状态信息
     * @param info 分页信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData><com.cskaoyan.bean.MarketCouponUser> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:33
     */

    @GetMapping("listuser")
    public BaseRespVo getCouponUserList(Integer couponId , Integer userId , Short status ,BasePageInfo info){
        CommonData data = adminCouponService.getCouponUserList(couponId,userId,status,info);
        return BaseRespVo.ok(data);
    }
}
