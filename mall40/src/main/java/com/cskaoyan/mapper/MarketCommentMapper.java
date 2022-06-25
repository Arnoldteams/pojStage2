package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.MarketCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketCommentMapper {
    long countByExample(MarketCommentExample example);

    int deleteByExample(MarketCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketComment record);

    int insertSelective(MarketComment record);

    List<MarketComment> selectByExample(MarketCommentExample example);

    MarketComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketComment record, @Param("example") MarketCommentExample example);

    int updateByExample(@Param("record") MarketComment record, @Param("example") MarketCommentExample example);

    int updateByPrimaryKeySelective(MarketComment record);

    int updateByPrimaryKey(MarketComment record);
}