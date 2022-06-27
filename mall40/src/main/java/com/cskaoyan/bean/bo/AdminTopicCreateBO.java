package com.cskaoyan.bean.bo;

import com.cskaoyan.bean.MarketTopic;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * 接收从客户端传递的数据
 * @author xyg2597@163.com
 * @since 2022/06/25 23:41
 */

@Data
public class AdminTopicCreateBO {
    private Integer id;

    private String title;

    private String subtitle;

    private String price;

    private String readCount;

    private String picUrl;

    private Integer[] goods;

    private String content;

//    private Date addTime;
//
//    private Date updateTime;
//
//    private Boolean deleted;
//
//    private Integer sortOrder;

    public static MarketTopic toCreateMarkTopic(MarketTopic marketTopic, AdminTopicCreateBO adminTopicCreateBO) {
        marketTopic.setContent(adminTopicCreateBO.getContent());
        marketTopic.setTitle(adminTopicCreateBO.getTitle());
        marketTopic.setSubtitle(adminTopicCreateBO.getSubtitle());
        marketTopic.setPicUrl(adminTopicCreateBO.getPicUrl());
        marketTopic.setReadCount(adminTopicCreateBO.getReadCount());
        marketTopic.setGoods(Arrays.toString(adminTopicCreateBO.getGoods()));
        return marketTopic;
    }

//    public static MarketTopic toUpdateMarkTopic(MarketTopic marketTopic, AdminTopicCreateBO adminTopicCreateBO) {
//        MarketTopic updateMarketTopic = toCreateMarkTopic(marketTopic, adminTopicCreateBO);
//        updateMarketTopic.setId(adminTopicCreateBO.getId());
//        updateMarketTopic.setAddTime(adminTopicCreateBO.getAddTime());
//        updateMarketTopic.setUpdateTime(adminTopicCreateBO.getUpdateTime());
//        updateMarketTopic.setDeleted(adminTopicCreateBO.getDeleted());
//        updateMarketTopic.setSortOrder(adminTopicCreateBO.getSortOrder());
//
//        return updateMarketTopic;
//    }
}
