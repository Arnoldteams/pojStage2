package com.cskaoyan.mapper;

import com.cskaoyan.bean.AdminCategoryOne;
import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryChildren;
import com.cskaoyan.bean.MarketCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCategoryMapper {
    long countByExample(MarketCategoryExample example);

    int deleteByExample(MarketCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCategory record);

    int insertSelective(MarketCategory record);

    List<MarketCategory> selectByExample(MarketCategoryExample example);

    MarketCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCategory record, @Param("example") MarketCategoryExample example);

    int updateByExample(@Param("record") MarketCategory record, @Param("example") MarketCategoryExample example);

    int updateByPrimaryKeySelective(MarketCategory record);

    int updateByPrimaryKey(MarketCategory record);


    List<AdminCategoryOne> selectL1Category(@Param("pid") Integer pid);

    List<MarketCategoryChildren> selectL1CategoryByPid(@Param("l1") String l1);

    List<MarketCategory> selectL2CategoryByLevelAndPid(@Param("l2") String l2, @Param("id") Integer id);

    List<Integer> selectCatIds(@Param("id") Integer id);

    List<MarketCategory> selectL2CategoryByPid(@Param("pid") Integer pid);
}

