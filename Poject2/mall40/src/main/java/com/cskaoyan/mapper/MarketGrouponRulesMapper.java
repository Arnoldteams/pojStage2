package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketGrouponRules;
import com.cskaoyan.bean.MarketGrouponRulesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketGrouponRulesMapper {
    long countByExample(MarketGrouponRulesExample example);

    int deleteByExample(MarketGrouponRulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGrouponRules record);

    int insertSelective(MarketGrouponRules record);

    List<MarketGrouponRules> selectByExample(MarketGrouponRulesExample example);

    MarketGrouponRules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGrouponRules record, @Param("example") MarketGrouponRulesExample example);

    int updateByExample(@Param("record") MarketGrouponRules record, @Param("example") MarketGrouponRulesExample example);

    int updateByPrimaryKeySelective(MarketGrouponRules record);

    int updateByPrimaryKey(MarketGrouponRules record);
}