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


    /*以下是商城首页的方法*/
    Integer selectGoodsTotal();
    Integer selectOrderTotal();
    Integer selectProductTotal();
    Integer selectUserTotal();

    /*以下是删除角色所需的查询所有角色id的方法*/
    String[] selectAllRoleId();
}