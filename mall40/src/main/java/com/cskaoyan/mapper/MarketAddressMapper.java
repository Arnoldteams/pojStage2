package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketAddressMapper {
    long countByExample(MarketAddressExample example);

    int deleteByExample(MarketAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAddress record);

    int insertSelective(MarketAddress record);

    List<MarketAddress> selectByExample(MarketAddressExample example);

    MarketAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAddress record, @Param("example") MarketAddressExample example);

    int updateByExample(@Param("record") MarketAddress record, @Param("example") MarketAddressExample example);

    int updateByPrimaryKeySelective(MarketAddress record);

    int updateByPrimaryKey(MarketAddress record);
}