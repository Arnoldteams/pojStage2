package com.cskaoyan.bean.bo.wxOrder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatusHandleConvert {
    NOT_PAY(101, new WxOrderListHandleOption().setAftersale(true)
            .setComment(true)
            .setDelete(true)
            .setRebuy(true)),

    USER_CANCALED(102, new WxOrderListHandleOption()),
    SYSTEM_CANCALED(103, new WxOrderListHandleOption()),
    PAYED(201, new WxOrderListHandleOption()),
    REFUND_APPLIED(202, new WxOrderListHandleOption()),
    REFUNDED(203, new WxOrderListHandleOption()),
    SHIPED(301, new WxOrderListHandleOption()),
    USER_RECEIVED(401, new WxOrderListHandleOption()),
    SYSTEM_RECEIVED(402, new WxOrderListHandleOption());


    private static Map OrderStatusMap;
    private Integer orderStatus;
    private WxOrderListHandleOption handler;


    OrderStatusHandleConvert() {
    }

    OrderStatusHandleConvert(Integer orderStatus, WxOrderListHandleOption handler) {
        this.orderStatus = orderStatus;
        this.handler = handler;
    }


    public Integer getOrderStatus() {
        return orderStatus;
    }

    public WxOrderListHandleOption getHandler() {
        return handler;
    }

    public static Map getInstance(){
        if(OrderStatusMap == null){
            HashMap<Integer, Object> map = new HashMap<>();
            for (OrderStatusContentConvert value : OrderStatusContentConvert.values()) {
                map.put(value.getOrderStatus(),value);
            }
            OrderStatusMap = map;
        }
        return OrderStatusMap;
    }

}
