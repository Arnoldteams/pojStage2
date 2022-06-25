package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketGoodsProduct;
import com.cskaoyan.bean.MarketGoodsProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketGoodsProductMapper {
    long countByExample(MarketGoodsProductExample example);

    int deleteByExample(MarketGoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGoodsProduct record);

    int insertSelective(MarketGoodsProduct record);

    List<MarketGoodsProduct> selectByExample(MarketGoodsProductExample example);

    MarketGoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGoodsProduct record, @Param("example") MarketGoodsProductExample example);

    int updateByExample(@Param("record") MarketGoodsProduct record, @Param("example") MarketGoodsProductExample example);

    int updateByPrimaryKeySelective(MarketGoodsProduct record);

    int updateByPrimaryKey(MarketGoodsProduct record);
}