package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxUserCouponVO;
import com.cskaoyan.mapper.MarketCartMapper;
import com.cskaoyan.mapper.MarketCouponMapper;
import com.cskaoyan.mapper.MarketCouponUserMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-29 14:12:22
 * @version:
 * @Description: 优惠券
 */

@Service
public class WxCouponServiceImpl implements WxCouponService {

    @Autowired
    MarketCouponMapper marketCouponMapper;

    @Autowired
    MarketCouponUserMapper marketCouponUserMapper;

    @Autowired
    MarketUserMapper marketUserMapper;

    @Autowired
    MarketCartMapper marketCartMapper;

    @Override
    public int getCouponForUser(String username, Integer couponId) {

        //得到对应的优惠券信息
        MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(couponId);

        if(couponId == -2){
            return -2;
        }

        if(username == null){
            return -1;
        }
        //优惠券的total值小于等于0，返回状态码0
        if(marketCoupon.getTotal() <= 0) {
            return 0;
        }

        //用户领取的优惠券数量已到最大值，返回状态码1
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        MarketCouponUserExample.Criteria criteria1 = marketCouponUserExample.createCriteria();
        criteria1.andCouponIdEqualTo(couponId);
        long l = marketCouponUserMapper.countByExample(marketCouponUserExample);
        if(Long.valueOf(String.valueOf(marketCoupon.getLimit())) == l ){
            return 1;
        }

        //对优惠券信息进行修改
        Integer total = marketCoupon.getTotal();
        Integer currentTotal = total - 1;
        Date currentDate = new Date(System.currentTimeMillis());
        MarketCoupon marketCoupon1 = new MarketCoupon();
        marketCoupon1.setUpdateTime(currentDate);
        marketCoupon1.setTotal(currentTotal);
        marketCoupon1.setId(couponId);
        int i = marketCouponMapper.updateByPrimaryKeySelective(marketCoupon1);
        System.out.println(i);

        //在优惠券用户信息表里增加一条优惠券信息
        MarketUserExample marketUserExample = new MarketUserExample();
        MarketUserExample.Criteria criteria2 = marketUserExample.createCriteria();
        criteria2.andUsernameEqualTo(username);
        List<MarketUser> marketUsers = marketUserMapper.selectByExample(marketUserExample);
        Integer id = marketUsers.get(0).getId();
        MarketCouponUser marketCouponUser = new MarketCouponUser();
        marketCouponUser.setUserId(id);
        marketCouponUser.setCouponId(couponId);
        marketCouponUser.setStatus(marketCoupon.getStatus());
        marketCouponUser.setStartTime(marketCoupon.getStartTime());
        marketCouponUser.setEndTime(marketCoupon.getEndTime());
        marketCouponUser.setAddTime(new Date(System.currentTimeMillis()));
        marketCouponUser.setUpdateTime(new Date(System.currentTimeMillis()));
        int i1 = marketCouponUserMapper.insertSelective(marketCouponUser);
        System.out.println(i1);
        return 2;

    }

    @Override
    public CommonData getUserCouponList(String username, Integer status,Integer cartId ,BasePageInfo info) {

        //获得User的id
        MarketUserExample marketUserExample = new MarketUserExample();
        MarketUserExample.Criteria criteria2 = marketUserExample.createCriteria();
        criteria2.andUsernameEqualTo(username);
        List<MarketUser> marketUsers = marketUserMapper.selectByExample(marketUserExample);
        Integer id = marketUsers.get(0).getId();

        //查询该用户拥有的优惠券
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        MarketCouponUserExample.Criteria criteria = marketCouponUserExample.createCriteria();
        criteria.andUserIdEqualTo(id).andStatusEqualTo(Short.valueOf(String.valueOf(status)));

        //进入购物车下单优惠券展示逻辑
        //判断用户是否需要展示优惠券，不存在值为1
        int listIsExist = 0;
        if(info.getLimit() == null) {
            MarketCartExample marketCartExample = new MarketCartExample();
            MarketCartExample.Criteria criteria1 = marketCartExample.createCriteria();
            if(cartId == 0) {
                criteria1.andUserIdEqualTo(id).andCheckedEqualTo(true);
            }else {
                criteria1.andUserIdEqualTo(id).andIdEqualTo(cartId);
            }
            List<MarketCart> marketCarts = marketCartMapper.selectByExample(marketCartExample);
            //获得购物车内选中商品的总金额
            BigDecimal bigDecimal = new BigDecimal(0);
            for (MarketCart marketCart : marketCarts) {
                BigDecimal price = marketCart.getPrice();
                Short number = marketCart.getNumber();
                price = price.multiply(new BigDecimal(number));
                bigDecimal = bigDecimal.add(price);
            }
            //获得用户拥有的所有优惠券id
            MarketCouponUserExample marketCouponUserExample1 = new MarketCouponUserExample();
            MarketCouponUserExample.Criteria criteria4 = marketCouponUserExample.createCriteria();
            criteria4.andUserIdEqualTo(id).andStatusEqualTo(Short.valueOf("0"));
            List<MarketCouponUser> marketCouponUsers = marketCouponUserMapper.selectByExample(marketCouponUserExample1);
            ArrayList<Integer> ids = new ArrayList<>();
            for (MarketCouponUser marketCouponUser : marketCouponUsers) {
                Integer couponId = marketCouponUser.getCouponId();
                ids.add(couponId);
            }

            //通过用户拥有的优惠券id和购物车内选中商品的总金额，得到应该在订单中展示的优惠券信息
            MarketCouponExample marketCouponExample = new MarketCouponExample();
            MarketCouponExample.Criteria criteria3 = marketCouponExample.createCriteria();
            criteria3.andIdIn(ids).andMinLessThan(bigDecimal);
            List<MarketCoupon> marketCoupons = marketCouponMapper.selectByExample(marketCouponExample);
            if (marketCoupons.size() == 0) {
                listIsExist = 1;
            }

            //将这些优惠券的id放在一个列表中
            ArrayList<Integer> ids1 = new ArrayList<>();
            for (MarketCoupon marketCoupon : marketCoupons) {
                ids1.add(marketCoupon.getId());
            }

            criteria.andCouponIdIn(ids1);
        }


        if(listIsExist == 0){
            long total = marketCouponUserMapper.countByExample(marketCouponUserExample);
            if (info.getLimit() == null) {
                info.setLimit(Integer.parseInt(String.valueOf(total)));
            }
            PageHelper.startPage(info.getPage(), info.getLimit());
            List<MarketCouponUser> marketCouponUsers = marketCouponUserMapper.selectByExample(marketCouponUserExample);
            //创建可以存入返回信息的列表
            LinkedList<WxUserCouponVO> userCoupons = new LinkedList<WxUserCouponVO>();
            for (int i = 0; i < marketCouponUsers.size(); i++) {
                MarketCouponUser marketCouponUser = marketCouponUsers.get(i);
                Integer couponId = marketCouponUser.getCouponId();
                WxUserCouponVO wxUserCouponVO = new WxUserCouponVO();
                wxUserCouponVO.setId(marketCouponUser.getId());
                wxUserCouponVO.setCid(couponId);
                MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(couponId);
                wxUserCouponVO.setName(marketCoupon.getName());
                wxUserCouponVO.setDesc(marketCoupon.getDesc());
                wxUserCouponVO.setTag(marketCoupon.getTag());
                wxUserCouponVO.setMin(marketCoupon.getMin());
                wxUserCouponVO.setDiscount(marketCoupon.getDiscount());
                if (marketCoupon.getStartTime() != null) {
                    wxUserCouponVO.setStartTime(marketCoupon.getStartTime());
                } else {
                    Date addTime = marketCouponUser.getAddTime();
                    wxUserCouponVO.setStartTime(addTime);
                }
                if (marketCoupon.getEndTime() != null) {
                    wxUserCouponVO.setEndTime(marketCoupon.getEndTime());
                } else {
                    Short days = marketCoupon.getDays();
                    Date date = marketCouponUser.getAddTime();
                    int currentDays = Integer.parseInt(String.valueOf(days));
                    date.setTime(date.getTime() + currentDays * 24 * 60 * 60 * 1000);
                    wxUserCouponVO.setEndTime(date);
                }

                userCoupons.add(wxUserCouponVO);
            }

            CommonData<WxUserCouponVO> commonData = new CommonData<>();
            commonData.setTotal(Integer.parseInt(String.valueOf(total)));
            commonData.setLimit(info.getLimit());
            commonData.setPages(1);
            commonData.setPage(1);
            commonData.setList(userCoupons);


            return commonData;
        }
        CommonData<WxUserCouponVO> commonData = new CommonData<>();
        commonData.setTotal(0);
        commonData.setLimit(info.getLimit());
        commonData.setPages(1);
        commonData.setPage(1);

        return commonData;


    }

    @Override
    public int addCouponByCode(String code) {
        MarketCouponExample marketCouponExample = new MarketCouponExample();
        MarketCouponExample.Criteria criteria = marketCouponExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<MarketCoupon> marketCoupons = marketCouponMapper.selectByExample(marketCouponExample);
        if(marketCoupons.size() == 0){
            return -2;
        }
        MarketCoupon marketCoupon = marketCoupons.get(0);
        Integer id = marketCoupon.getId();
        return id;
    }
}
