package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxCouponRespVO;
import com.cskaoyan.bean.vo.WxUserCouponVO;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminCouponService;
import com.cskaoyan.service.WxCouponService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @Autowired
    WxCouponService wxCouponService;


    /**
     * 在首页中展示全部优惠券信息
     *
     * @param info 分页信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData><com.cskaoyan.bean.MarketCoupon> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/29 9:11
     */


    @RequestMapping("list")
    public BaseRespVo getAllList(String name,Short type , Short status,BasePageInfo info){
        CommonData couponList = adminCouponService.getCouponList(name, type, status, info);
        return BaseRespVo.ok(couponList);
    }


    /**
     * 根据条件为用户领取优惠券
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 17:22
     */


    @RequestMapping("receive")
    public WxCouponRespVO getCouponForUser(@RequestBody Map map){
        Integer couponId = (Integer) map.get("couponId");
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        if(subject.isAuthenticated()){
            MarketUser marketUser = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
            username = marketUser.getUsername();
        }
        int statusId = wxCouponService.getCouponForUser(username, couponId);
        WxCouponRespVO wxCouponRespVO = new WxCouponRespVO();
        if(statusId == 0){
            wxCouponRespVO.setErrmsg("优惠券已领完");
            wxCouponRespVO.setErrno(740);
        }else if(statusId == 1){
            wxCouponRespVO.setErrmsg("优惠券已经领取");
            wxCouponRespVO.setErrno(740);
        }else if (statusId == 2){
            wxCouponRespVO.setErrmsg("成功");
            wxCouponRespVO.setErrno(0);
        }else if(statusId == -1){
            wxCouponRespVO.setErrmsg("请登录");
            wxCouponRespVO.setErrno(501);
        }

        return wxCouponRespVO;
    }

    /**
     * 展示用户个人优惠券信息
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 17:23
     */


    @RequestMapping("mylist")
    public BaseRespVo getUserList(Integer status,BasePageInfo info){
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        if(subject.isAuthenticated()){
            MarketUser marketUser = (MarketUser)subject.getPrincipals().getPrimaryPrincipal();
            username=marketUser.getUsername();
        }

        CommonData userCouponList = wxCouponService.getUserCouponList(username, status,null, info);
        return BaseRespVo.ok(userCouponList);
    }

    /**
     * 展示用户订单中的优惠券信息
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 17:23
     */


    @GetMapping("selectlist")
    public BaseRespVo getUserCartList(Integer cartId , Integer grouponRulesId){
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        if(subject.isAuthenticated()){
            MarketUser marketUser = (MarketUser)subject.getPrincipals().getPrimaryPrincipal();
            username=marketUser.getUsername();
        }
        BasePageInfo basePageInfo = new BasePageInfo();
        basePageInfo.setPage(1);
        CommonData userCouponList = wxCouponService.getUserCouponList(username, 0,cartId, basePageInfo);
        return BaseRespVo.ok(userCouponList);
    }

    /**
     * 通过兑换码为用户领取优惠券
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 17:23
     */


    @RequestMapping("exchange")
    public WxCouponRespVO addCouponByCode(@RequestBody Map map) {
        String code = (String) map.get("code");
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        if (subject.isAuthenticated()) {
            MarketUser marketUser = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
            username = marketUser.getUsername();
        }
        int couponId = wxCouponService.addCouponByCode(code);
        int statusId = wxCouponService.getCouponForUser(username, couponId);
        WxCouponRespVO wxCouponRespVO = new WxCouponRespVO();
        if (statusId == 0) {
            wxCouponRespVO.setErrmsg("优惠券已领完");
            wxCouponRespVO.setErrno(740);
        } else if (statusId == 1) {
            wxCouponRespVO.setErrmsg("优惠券已经领取");
            wxCouponRespVO.setErrno(740);
        } else if (statusId == 2) {
            wxCouponRespVO.setErrmsg("成功");
            wxCouponRespVO.setErrno(0);
        } else if (statusId == -1) {
            wxCouponRespVO.setErrmsg("请登录");
            wxCouponRespVO.setErrno(501);
        } else if (statusId == -2) {
            wxCouponRespVO.setErrmsg("优惠券不正确");
            wxCouponRespVO.setErrno(742);
        }
        return wxCouponRespVO;
    }
}
