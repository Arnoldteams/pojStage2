package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketOrderMapper {
    long countByExample(MarketOrderExample example);

    int deleteByExample(MarketOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrder record);

    int insertSelective(MarketOrder record);

    List<MarketOrder> selectByExample(MarketOrderExample example);

    MarketOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByExample(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByPrimaryKeySelective(MarketOrder record);

    int updateByPrimaryKey(MarketOrder record);
}