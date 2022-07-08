package com.cskaoyan.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 17:37:11
 * @version:
 * @Description:
 */
@Data
public class WxOrderDetailVO {

    private List<?> expressInfo;
    private OrderInfoEntity orderInfo;
    private List<OrderGoodsEntity> orderGoods;

    @Data
    public static class OrderInfoEntity {

        private String expName;
        private String consignee;
        private String address;
        private String addTime;
        private String orderSn;
        private double actualPrice;
        private String mobile;
        private String message;
        private String expCode;
        private String orderStatusText;
        private int aftersaleStatus;
        private double goodsPrice;
        private String expNo;
        private double couponPrice;
        private int id;
        private double freightPrice;
        private HandleOptionEntity handleOption;

        @Data
        public static class HandleOptionEntity {

            private boolean cancel;
            private boolean confirm;
            private boolean rebuy;
            private boolean pay;
            private boolean comment;
            private boolean delete;
            private boolean aftersale;
            private boolean refund;
        }

    }

    @Data
    public static class OrderGoodsEntity {

        private int productId;
        private String addTime;
        private int orderId;
        private int goodsId;
        private String goodsSn;
        private String updateTime;
        private List<String> specifications;
        private int number;
        private String picUrl;
        private boolean deleted;
        private double price;
        private int comment;
        private int id;
        private String goodsName;

    }

}
