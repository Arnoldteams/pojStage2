package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCouponUser;
import com.cskaoyan.bean.MarketCouponUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCouponUserMapper {
    long countByExample(MarketCouponUserExample example);

    int deleteByExample(MarketCouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCouponUser record);

    int insertSelective(MarketCouponUser record);

    List<MarketCouponUser> selectByExample(MarketCouponUserExample example);

    MarketCouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCouponUser record, @Param("example") MarketCouponUserExample example);

    int updateByExample(@Param("record") MarketCouponUser record, @Param("example") MarketCouponUserExample example);

    int updateByPrimaryKeySelective(MarketCouponUser record);

    int updateByPrimaryKey(MarketCouponUser record);
}