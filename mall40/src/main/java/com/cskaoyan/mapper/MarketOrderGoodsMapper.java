package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketOrderGoods;
import com.cskaoyan.bean.MarketOrderGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketOrderGoodsMapper {
    long countByExample(MarketOrderGoodsExample example);

    int deleteByExample(MarketOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrderGoods record);

    int insertSelective(MarketOrderGoods record);

    List<MarketOrderGoods> selectByExample(MarketOrderGoodsExample example);

    MarketOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrderGoods record, @Param("example") MarketOrderGoodsExample example);

    int updateByExample(@Param("record") MarketOrderGoods record, @Param("example") MarketOrderGoodsExample example);

    int updateByPrimaryKeySelective(MarketOrderGoods record);

    int updateByPrimaryKey(MarketOrderGoods record);
}