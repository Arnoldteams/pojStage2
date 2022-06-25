package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.MarketIssueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketIssueMapper {
    long countByExample(MarketIssueExample example);

    int deleteByExample(MarketIssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketIssue record);

    int insertSelective(MarketIssue record);

    List<MarketIssue> selectByExample(MarketIssueExample example);

    MarketIssue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketIssue record, @Param("example") MarketIssueExample example);

    int updateByExample(@Param("record") MarketIssue record, @Param("example") MarketIssueExample example);

    int updateByPrimaryKeySelective(MarketIssue record);

    int updateByPrimaryKey(MarketIssue record);
}