package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.MarketCartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCartMapper {
    long countByExample(MarketCartExample example);

    int deleteByExample(MarketCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCart record);

    int insertSelective(MarketCart record);

    List<MarketCart> selectByExample(MarketCartExample example);

    MarketCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByExample(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByPrimaryKeySelective(MarketCart record);

    int updateByPrimaryKey(MarketCart record);
}