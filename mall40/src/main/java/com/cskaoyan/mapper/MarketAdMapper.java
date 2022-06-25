package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketAd;
import com.cskaoyan.bean.MarketAdExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketAdMapper {
    long countByExample(MarketAdExample example);

    int deleteByExample(MarketAdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAd record);

    int insertSelective(MarketAd record);

    List<MarketAd> selectByExample(MarketAdExample example);

    MarketAd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAd record, @Param("example") MarketAdExample example);

    int updateByExample(@Param("record") MarketAd record, @Param("example") MarketAdExample example);

    int updateByPrimaryKeySelective(MarketAd record);

    int updateByPrimaryKey(MarketAd record);
}