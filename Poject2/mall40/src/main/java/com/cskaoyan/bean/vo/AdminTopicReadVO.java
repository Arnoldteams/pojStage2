package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketTopic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑专题信息回显
 *
 * @author xyg2597@163.com
 * @since 2022/06/26 13:05
 */
@Data
public class AdminTopicReadVO {

//    专题信息封装的对象
    private MarketTopic topic;

//    该专题信息包含的商品列表
    private List<TopicReadGoodsVO> goodsList;

}
