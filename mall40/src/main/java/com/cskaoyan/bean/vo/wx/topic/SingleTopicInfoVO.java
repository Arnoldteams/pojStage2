package com.cskaoyan.bean.vo.wx.topic;

import com.cskaoyan.bean.MarketTopic;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 单个专题返回的信息
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 17:03
 */
@Data
public class SingleTopicInfoVO {
    private Integer id;

    private String title;

    private String subtitle;

    private BigDecimal price;

    private String readCount;

    private String picUrl;

    public static SingleTopicInfoVO setSingleTopicInfoVO(MarketTopic marketTopic){

        SingleTopicInfoVO singleTopicInfoVO = new SingleTopicInfoVO();

        singleTopicInfoVO.setId(marketTopic.getId());
        singleTopicInfoVO.setTitle(marketTopic.getTitle());
        singleTopicInfoVO.setSubtitle(marketTopic.getSubtitle());
        singleTopicInfoVO.setPrice(marketTopic.getPrice());
        singleTopicInfoVO.setReadCount(marketTopic.getReadCount());
        singleTopicInfoVO.setPicUrl(marketTopic.getPicUrl());
        return singleTopicInfoVO;
    }

}
