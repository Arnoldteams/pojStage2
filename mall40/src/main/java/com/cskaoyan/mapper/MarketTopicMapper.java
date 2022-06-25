package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.MarketTopicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketTopicMapper {
    long countByExample(MarketTopicExample example);

    int deleteByExample(MarketTopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketTopic record);

    int insertSelective(MarketTopic record);

    List<MarketTopic> selectByExampleWithBLOBs(MarketTopicExample example);

    List<MarketTopic> selectByExample(MarketTopicExample example);

    MarketTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketTopic record, @Param("example") MarketTopicExample example);

    int updateByExampleWithBLOBs(@Param("record") MarketTopic record, @Param("example") MarketTopicExample example);

    int updateByExample(@Param("record") MarketTopic record, @Param("example") MarketTopicExample example);

    int updateByPrimaryKeySelective(MarketTopic record);

    int updateByPrimaryKeyWithBLOBs(MarketTopic record);

    int updateByPrimaryKey(MarketTopic record);
}