package com.cskaoyan.bean.bo.wxOrder;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatusConvert {
    NOT_PAY(1,101),
    PAYED(2,201),
    NOT_SHIP(3,301),
    SHIPED(4,401);

    private static Map OrderStatusMap;
    private Integer showType;
    private Integer orderStatus;

    OrderStatusConvert() {
    }

    OrderStatusConvert(Integer showType, Integer orderStatus){
        this.orderStatus = orderStatus;
        this.showType = showType;
    }

    public Integer getShowType() {
        return showType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public static Integer getStatus(Integer showType){
        OrderStatusConvert[] values = OrderStatusConvert.values();
        for (OrderStatusConvert value : values) {
            if(value.getShowType().equals(showType)){
                return value.getOrderStatus();
            }
        }
        return null;
    }

    public static Map getInstance(){
        if(OrderStatusMap == null){
            HashMap<Integer, Object> map = new HashMap<>();
            for (OrderStatusConvert value : OrderStatusConvert.values()) {
                map.put(value.getOrderStatus(),value);
                map.put(value.getShowType(),value);
            }
            OrderStatusMap = map;
        }
        return OrderStatusMap;
    }
}
