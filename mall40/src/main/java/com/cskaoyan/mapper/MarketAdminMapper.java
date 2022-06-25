package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.MarketAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketAdminMapper {
    long countByExample(MarketAdminExample example);

    int deleteByExample(MarketAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAdmin record);

    int insertSelective(MarketAdmin record);

    List<MarketAdmin> selectByExample(MarketAdminExample example);

    MarketAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAdmin record, @Param("example") MarketAdminExample example);

    int updateByExample(@Param("record") MarketAdmin record, @Param("example") MarketAdminExample example);

    int updateByPrimaryKeySelective(MarketAdmin record);

    int updateByPrimaryKey(MarketAdmin record);
}