package com.cskaoyan.bean.vo;

import lombok.Data;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 10:04:19
 * @version:
 * @Description:
 */
@Data
public class AdminConfigVO {

    // mall
    private String market_mall_longitude;
    private String market_mall_latitude;
    private String market_mall_address;
    private String market_mall_qq;
    private String market_mall_phone;
    private String market_mall_name;

    // express
    private String market_express_freight_min;
    private String market_express_freight_value;

    // order
    private String market_order_unconfirm;
    private String market_order_unpaid;
    private String market_order_comment;

    // wx
    private String market_wx_index_new;
    private String market_wx_index_topic;
    private String market_wx_share;
    private String market_wx_index_brand;
    private String market_wx_catlog_goods;
    private String market_wx_catlog_list;
    private String market_wx_index_hot;
}
