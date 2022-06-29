package com.cskaoyan.bean.vo.wx.topic;

import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.vo.TopicReadGoodsVO;
import lombok.Data;

import java.util.List;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 17:44
 */
@Data
public class WxTopicDetailVO {

    MarketTopic topic;

    List<TopicReadGoodsVO> goods;
}
