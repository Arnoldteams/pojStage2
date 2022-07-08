package com.cskaoyan.bean.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 10:04:19
 * @version:
 * @Description:
 */
@Data
public class AdminConfigBO {

    // mall
    private String market_mall_longitude;
    private String market_mall_latitude;
    private String market_mall_address;
    private String market_mall_qq;

    @Length(min=11,max=11)
    @DecimalMin("0")
    private String market_mall_phone;
    private String market_mall_name;

    // express
    @DecimalMin("0")
    private String market_express_freight_min;
    @DecimalMin("0")
    private String market_express_freight_value;

    // order
    @Size(min=0,max=30)
    private String market_order_unconfirm;
    @Size(min=0,max=30)
    private String market_order_unpaid;
    @Size(min=0,max=30)
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
