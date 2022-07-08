package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketFeedback;
import com.cskaoyan.bean.MarketFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketFeedbackMapper {
    long countByExample(MarketFeedbackExample example);

    int deleteByExample(MarketFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketFeedback record);

    int insertSelective(MarketFeedback record);

    List<MarketFeedback> selectByExample(MarketFeedbackExample example);

    MarketFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketFeedback record, @Param("example") MarketFeedbackExample example);

    int updateByExample(@Param("record") MarketFeedback record, @Param("example") MarketFeedbackExample example);

    int updateByPrimaryKeySelective(MarketFeedback record);

    int updateByPrimaryKey(MarketFeedback record);
}