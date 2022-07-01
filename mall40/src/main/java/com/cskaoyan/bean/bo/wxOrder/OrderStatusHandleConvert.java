package com.cskaoyan.bean.bo.wxOrder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatusHandleConvert {
    NOT_PAY(101, new WxOrderListHandleOption().setCancel(true).setPay(true)),
    USER_CANCALED(102, new WxOrderListHandleOption().setDelete(true)),
    SYSTEM_CANCALED(103, new WxOrderListHandleOption().setDelete(true)),
    PAYED(201, new WxOrderListHandleOption().setRefund(true)),
    REFUND_APPLIED(202, new WxOrderListHandleOption()),
    REFUNDED(203, new WxOrderListHandleOption().setDelete(true)),
    SHIPED(301, new WxOrderListHandleOption().setConfirm(true)),
    USER_RECEIVED(401, new WxOrderListHandleOption().setAftersale(true).setComment(true).
            setDelete(true).setRebuy(true)),
    SYSTEM_RECEIVED(402, new WxOrderListHandleOption().setAftersale(true).setComment(true).
            setDelete(true).setRebuy(true));



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

    public static WxOrderListHandleOption getOption(Integer orderStatus){
        OrderStatusHandleConvert[] values = OrderStatusHandleConvert.values();
        for (OrderStatusHandleConvert value : values) {
            if(value.getOrderStatus().equals(orderStatus)){
                return value.getHandler();
            }
        }
        return null;
    }

    public static Map getInstance(){
        if(OrderStatusMap == null){
            HashMap<Integer, Object> map = new HashMap<>();
            for (OrderStatusHandleConvert value : OrderStatusHandleConvert.values()) {
                map.put(value.getOrderStatus(),value);
            }
            OrderStatusMap = map;
        }
        return OrderStatusMap;
    }

}
