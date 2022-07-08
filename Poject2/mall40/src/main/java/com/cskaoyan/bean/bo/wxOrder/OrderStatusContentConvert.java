package com.cskaoyan.bean.bo.wxOrder;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatusContentConvert {
    NOT_PAY(101,"未付款"),
    USER_CANCALED(102,"用户取消"),
    SYSTEM_CANCALED(103,"系统取消"),
    PAYED(201,"已付款"),
    REFUND_APPLIED(202,"订单取消，退款中"),
    REFUNDED(203,"已退款"),
    SHIPED(301,"已发货"),
    USER_RECEIVED(401,"用户收货"),
    SYSTEM_RECEIVED(402,"系统收货");


    private static Map OrderStatusMap;
    private Integer orderStatus;
    private String orderStatusContent;

    OrderStatusContentConvert() {
    }

    OrderStatusContentConvert(Integer orderStatus, String orderStatusContent) {
        this.orderStatus = orderStatus;
        this.orderStatusContent = orderStatusContent;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public String getOrderStatusContent() {
        return orderStatusContent;
    }

    public static String getStatusContent(Integer orderStatus){
        OrderStatusContentConvert[] values = OrderStatusContentConvert.values();
        for (OrderStatusContentConvert value : values) {
            if(value.getOrderStatus().equals(orderStatus)){
                return value.getOrderStatusContent();
            }
        }
        return null;
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
