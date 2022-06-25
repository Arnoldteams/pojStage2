package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCategoryMapper {
    long countByExample(MarketCategoryExample example);

    int deleteByExample(MarketCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCategory record);

    int insertSelective(MarketCategory record);

    List<MarketCategory> selectByExample(MarketCategoryExample example);

    MarketCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCategory record, @Param("example") MarketCategoryExample example);

    int updateByExample(@Param("record") MarketCategory record, @Param("example") MarketCategoryExample example);

    int updateByPrimaryKeySelective(MarketCategory record);

    int updateByPrimaryKey(MarketCategory record);
}