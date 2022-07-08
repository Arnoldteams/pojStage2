package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketNotice;
import com.cskaoyan.bean.MarketNoticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketNoticeMapper {
    long countByExample(MarketNoticeExample example);

    int deleteByExample(MarketNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketNotice record);

    int insertSelective(MarketNotice record);

    List<MarketNotice> selectByExample(MarketNoticeExample example);

    MarketNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketNotice record, @Param("example") MarketNoticeExample example);

    int updateByExample(@Param("record") MarketNotice record, @Param("example") MarketNoticeExample example);

    int updateByPrimaryKeySelective(MarketNotice record);

    int updateByPrimaryKey(MarketNotice record);
}