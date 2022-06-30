package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.MarketCouponBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketCouponMapper;
import com.cskaoyan.mapper.MarketCouponUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-26 16:48:16
 * @version:
 * @Description:优惠券管理的业务实现类
 */

@Service
public class AdminCouponServiceImpl implements AdminCouponService {

    @Autowired
    MarketCouponMapper marketCouponMapper;

    @Autowired
    MarketCouponUserMapper marketCouponUserMapper;


    /**
     * 根据条件展示优惠券信息
     *
     * @param name 优惠券名字
     * @param type 优惠券类型
     * @param status 优惠券使用状态
     * @param info 分页信息
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketCoupon> 页内信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:54
     */

    @Override
    public CommonData getCouponList(String name , Short type , Short status ,BasePageInfo info) {
        PageHelper.startPage(info.getPage(),info.getLimit());
        MarketCouponExample marketCouponUserExample = new MarketCouponExample();
        MarketCouponExample.Criteria criteria1 = marketCouponUserExample.createCriteria();
        if(!StringUtils.isEmpty(name)){
            criteria1.andNameLike('%'+name +'%');
        }
        if(!(type == null)){
            criteria1.andTypeEqualTo(type);
        }
        if(!(status == null)){
            criteria1.andStatusEqualTo(status);
        }
        if(info.getSort()!=null&&info.getOrder()!=null) {
            marketCouponUserExample.setOrderByClause(info.getSort() + " " + info.getOrder());
        }
        List<MarketCoupon> marketCoupons = marketCouponMapper.selectByExample(marketCouponUserExample);
        PageInfo<MarketCoupon> marketCouponPageInfo = new PageInfo<>(marketCoupons);
        return CommonData.data(marketCouponPageInfo);
    }


    /**
     * 根据条件更改优惠券信息
     *
     * @param marketCoupon 优惠券信息
     * @return com.cskaoyan.bean.MarketCoupon 优惠券信息
     * @author yn1609853@163.com
     * @since 2022/6/27 18:58
     */

    @Override
    public MarketCoupon changeCouponInfo(MarketCoupon marketCoupon) {
        Date currentDate = new Date(System.currentTimeMillis());
        marketCoupon.setUpdateTime(currentDate);
        int i = marketCouponMapper.updateByPrimaryKeySelective(marketCoupon);
        System.out.println(i);
        return marketCoupon;
    }


    /**
     * 根据条件增加优惠券信息
     *
     *
     * @param marketCouponBO 优惠券信息(自制)
     * @return com.cskaoyan.bean.bo.MarketCouponBO 优惠券信息(自制)
     * @author yn1609853@163.com
     * @since 2022/6/27 19:00
     */

    @Override
    public MarketCouponBO addCoupon(MarketCouponBO marketCouponBO) {
        Date currentDate = new Date(System.currentTimeMillis());
        marketCouponBO.setAddTime(currentDate);
        marketCouponBO.setUpdateTime(currentDate);
        MarketCoupon marketCoupon = new MarketCoupon();
        marketCoupon.setDays(marketCouponBO.getDays());
        marketCoupon.setDesc(marketCouponBO.getDesc());
        marketCoupon.setDiscount(marketCouponBO.getDiscount());
        marketCoupon.setEndTime(marketCouponBO.getEndTime());
        marketCoupon.setGoodsType(marketCouponBO.getGoodsType());
        String[] goodsValue = marketCouponBO.getGoodsValue();
        StringBuilder goodsValueString = new StringBuilder("[");
        for (int i = 0; i < goodsValue.length; i++) {
            goodsValueString.append(goodsValue[i]);
        }
        goodsValueString.append("]");
        marketCoupon.setGoodsValue(goodsValueString.toString());
        marketCoupon.setLimit(marketCouponBO.getLimit());
        marketCoupon.setMin(marketCouponBO.getMin());
        marketCoupon.setName(marketCouponBO.getName());
        marketCoupon.setStartTime(marketCouponBO.getStartTime());
        marketCoupon.setEndTime(marketCouponBO.getEndTime());
        marketCoupon.setStatus(marketCouponBO.getStatus());
        marketCoupon.setTag(marketCouponBO.getTag());
        marketCoupon.setTimeType(marketCouponBO.getTimeType());
        marketCoupon.setTotal(marketCouponBO.getTotal());
        marketCoupon.setType(marketCouponBO.getType());
        marketCoupon.setAddTime(marketCouponBO.getAddTime());
        marketCoupon.setUpdateTime(marketCouponBO.getUpdateTime());
        int i = marketCouponMapper.insertSelective(marketCoupon);
        System.out.println(i);
        marketCouponBO.setId(marketCoupon.getId());
        //获取随机八位兑换码
        marketCouponBO.setCode(new AdminCouponServiceImpl().genRandomNum());
        return  marketCouponBO;
    }


    /**
     * 根据条件删除优惠券信息
     *
     * @param marketCoupon 优惠券信息
     * @return java.lang.boolean 布尔值
     * @author yn1609853@163.com
     * @since 2022/6/27 19:01
     */

    @Override
    public Boolean deleteCouponById(MarketCoupon marketCoupon) {
        int i = marketCouponMapper.deleteByPrimaryKey(marketCoupon.getId());
        System.out.println(i);
        return i > 0;
    }


    /**
     * 根据优惠券编号获得优惠券信息
     *
     * @param id 优惠券编号
     * @return com.cskaoyan.bean.MarketGroupon 优惠券信息
     * @author yn1609853@163.com
     * @since 2022/6/27 19:02
     */

    @Override
    public MarketCoupon getCouponById(Integer id) {
        MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(id);
        return marketCoupon;
    }

    /**
     * 根据条件获得优惠券使用者信息
     *
     * @param couponId 优惠券编号
     * @param userId 优惠券使用者编号
     * @param status 优惠券使用状态
     * @param info 分页信息
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketCouponUser> 页内信息
     * @author yn1609853@163.com
     * @since 2022/6/27 19:04
     */


    @Override
    public CommonData getCouponUserList(Integer couponId, Integer userId, Short status, BasePageInfo info) {
        PageHelper.startPage(info.getPage(),info.getLimit());
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        MarketCouponUserExample.Criteria criteria1 = marketCouponUserExample.createCriteria();
        //一定需要用couponId作为查询条件
        criteria1.andCouponIdEqualTo(couponId);
        if(!(userId == null)){
            criteria1.andUserIdEqualTo(userId);
        }
        if(!(status == null)){
            criteria1.andStatusEqualTo(status);
        }
        marketCouponUserExample.setOrderByClause(info.getSort()+" "+info.getOrder());
        List<MarketCouponUser> marketCouponUsers = marketCouponUserMapper.selectByExample(marketCouponUserExample);
        PageInfo<MarketCouponUser> marketCouponUserPageInfo = new PageInfo<>(marketCouponUsers);
        return CommonData.data(marketCouponUserPageInfo);

    }


    //获取优惠券兑换码(八位)
    public String genRandomNum(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 8){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }
}
