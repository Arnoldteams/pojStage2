package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketSystem;
import com.cskaoyan.bean.MarketSystemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketSystemMapper {
    long countByExample(MarketSystemExample example);

    int deleteByExample(MarketSystemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketSystem record);

    int insertSelective(MarketSystem record);

    List<MarketSystem> selectByExample(MarketSystemExample example);

    MarketSystem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketSystem record, @Param("example") MarketSystemExample example);

    int updateByExample(@Param("record") MarketSystem record, @Param("example") MarketSystemExample example);

    int updateByPrimaryKeySelective(MarketSystem record);

    int updateByPrimaryKey(MarketSystem record);
}