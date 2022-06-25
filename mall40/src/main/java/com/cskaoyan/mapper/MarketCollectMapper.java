package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketCollectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCollectMapper {
    long countByExample(MarketCollectExample example);

    int deleteByExample(MarketCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCollect record);

    int insertSelective(MarketCollect record);

    List<MarketCollect> selectByExample(MarketCollectExample example);

    MarketCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCollect record, @Param("example") MarketCollectExample example);

    int updateByExample(@Param("record") MarketCollect record, @Param("example") MarketCollectExample example);

    int updateByPrimaryKeySelective(MarketCollect record);

    int updateByPrimaryKey(MarketCollect record);
}