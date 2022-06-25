package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.MarketUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketUserMapper {
    long countByExample(MarketUserExample example);

    int deleteByExample(MarketUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketUser record);

    int insertSelective(MarketUser record);

    List<MarketUser> selectByExample(MarketUserExample example);

    MarketUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketUser record, @Param("example") MarketUserExample example);

    int updateByExample(@Param("record") MarketUser record, @Param("example") MarketUserExample example);

    int updateByPrimaryKeySelective(MarketUser record);

    int updateByPrimaryKey(MarketUser record);
}