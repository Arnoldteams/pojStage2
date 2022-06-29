package com.cskaoyan.bean;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    NOT_PAY(1,101),
    PAYED(2,201);

    private static Map OrderStatusMap;
    private Integer showType;
    private Integer orderStatus;

    OrderStatus() {
    }

    OrderStatus(Integer showType, Integer orderStatus){
        this.orderStatus = orderStatus;
        this.showType = showType;
    }

    public Integer getShowType() {
        return showType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public static Map getInstance(){
        if(OrderStatusMap == null){
            HashMap<Integer, Object> map = new HashMap<>();
            for (OrderStatus value : OrderStatus.values()) {
                map.put(value.getOrderStatus(),value);
                map.put(value.getShowType(),value);
            }
            OrderStatusMap = map;
        }
        return OrderStatusMap;
    }
}
