package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketGoodsAttribute;
import com.cskaoyan.bean.MarketGoodsAttributeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketGoodsAttributeMapper {
    long countByExample(MarketGoodsAttributeExample example);

    int deleteByExample(MarketGoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGoodsAttribute record);

    int insertSelective(MarketGoodsAttribute record);

    List<MarketGoodsAttribute> selectByExample(MarketGoodsAttributeExample example);

    MarketGoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGoodsAttribute record, @Param("example") MarketGoodsAttributeExample example);

    int updateByExample(@Param("record") MarketGoodsAttribute record, @Param("example") MarketGoodsAttributeExample example);

    int updateByPrimaryKeySelective(MarketGoodsAttribute record);

    int updateByPrimaryKey(MarketGoodsAttribute record);
}