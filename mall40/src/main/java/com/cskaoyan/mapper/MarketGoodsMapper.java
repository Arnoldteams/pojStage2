package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketGoodsMapper {
    long countByExample(MarketGoodsExample example);

    int deleteByExample(MarketGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGoods record);

    int insertSelective(MarketGoods record);

    List<MarketGoods> selectByExampleWithBLOBs(MarketGoodsExample example);

    List<MarketGoods> selectByExample(MarketGoodsExample example);

    MarketGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGoods record, @Param("example") MarketGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") MarketGoods record, @Param("example") MarketGoodsExample example);

    int updateByExample(@Param("record") MarketGoods record, @Param("example") MarketGoodsExample example);

    int updateByPrimaryKeySelective(MarketGoods record);

    int updateByPrimaryKeyWithBLOBs(MarketGoods record);

    int updateByPrimaryKey(MarketGoods record);
}