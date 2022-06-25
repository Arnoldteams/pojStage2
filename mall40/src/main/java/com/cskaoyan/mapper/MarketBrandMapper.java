package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.MarketBrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketBrandMapper {
    long countByExample(MarketBrandExample example);

    int deleteByExample(MarketBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketBrand record);

    int insertSelective(MarketBrand record);

    List<MarketBrand> selectByExample(MarketBrandExample example);

    MarketBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketBrand record, @Param("example") MarketBrandExample example);

    int updateByExample(@Param("record") MarketBrand record, @Param("example") MarketBrandExample example);

    int updateByPrimaryKeySelective(MarketBrand record);

    int updateByPrimaryKey(MarketBrand record);
}