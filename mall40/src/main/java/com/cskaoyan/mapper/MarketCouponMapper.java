package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCoupon;
import com.cskaoyan.bean.MarketCouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCouponMapper {
    long countByExample(MarketCouponExample example);

    int deleteByExample(MarketCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCoupon record);

    int insertSelective(MarketCoupon record);

    List<MarketCoupon> selectByExample(MarketCouponExample example);

    MarketCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCoupon record, @Param("example") MarketCouponExample example);

    int updateByExample(@Param("record") MarketCoupon record, @Param("example") MarketCouponExample example);

    int updateByPrimaryKeySelective(MarketCoupon record);

    int updateByPrimaryKey(MarketCoupon record);
}