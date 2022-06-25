package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.bean.MarketLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketLogMapper {
    long countByExample(MarketLogExample example);

    int deleteByExample(MarketLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketLog record);

    int insertSelective(MarketLog record);

    List<MarketLog> selectByExample(MarketLogExample example);

    MarketLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketLog record, @Param("example") MarketLogExample example);

    int updateByExample(@Param("record") MarketLog record, @Param("example") MarketLogExample example);

    int updateByPrimaryKeySelective(MarketLog record);

    int updateByPrimaryKey(MarketLog record);
}