package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketCoupon;
import com.cskaoyan.bean.bo.MarketCouponBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminCouponService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @Autowired
    HttpSession session;

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

    @RequiresPermissions(value = {"admin:coupon:list","*"},logical = Logical.OR)
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

    @LogAnnotation(value = "修改某个优惠券的信息",successResult = "修改成功",unSuccessResult = "修改失败")
    @RequiresPermissions(value = {"admin:coupon:update","*"},logical = Logical.OR)
    @RequestMapping("update")
    public BaseRespVo changeCouponInfo(@RequestBody MarketCoupon marketCoupon){
        MarketCoupon updatedMarketCoupon = adminCouponService.changeCouponInfo(marketCoupon);
        session.setAttribute("log",marketCoupon.getName());
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

    @LogAnnotation(value = "新增某条优惠券的信息",successResult = "添加成功",unSuccessResult = "添加失败")
    @RequiresPermissions(value = {"admin:coupon:create","*"},logical = Logical.OR)
    @RequestMapping("create")
    public BaseRespVo addCoupon(@RequestBody MarketCouponBO marketCouponBO){
        MarketCouponBO addedCouponBO = adminCouponService.addCoupon(marketCouponBO);
        session.setAttribute("log",marketCouponBO.getName());
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

    @LogAnnotation(value = "删除某张优惠券的信息",successResult = "删除成功",unSuccessResult = "删除失败")
    @RequiresPermissions(value = {"admin:coupon:delete","*"},logical = Logical.OR)
    @PostMapping("delete")
    public BaseRespVo deleteCoupon(@RequestBody MarketCoupon marketCoupon){
        Boolean aBoolean = adminCouponService.deleteCouponById(marketCoupon);
        BaseRespVo<String> baseRespVo = new BaseRespVo<>();
        if(aBoolean){
            return baseRespVo;
        }
        baseRespVo.setErrmsg("失败");
        session.setAttribute("log",marketCoupon.getName());
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

    @RequiresPermissions(value = {"admin:coupon:read","*"},logical = Logical.OR)
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

    @RequiresPermissions(value = {"admin:coupon:listuser","*"},logical = Logical.OR)
    @GetMapping("listuser")
    public BaseRespVo getCouponUserList(Integer couponId , Integer userId , Short status ,BasePageInfo info){
        CommonData data = adminCouponService.getCouponUserList(couponId,userId,status,info);
        return BaseRespVo.ok(data);
    }
}
