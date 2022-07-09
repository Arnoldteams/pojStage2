package com.cskaoyan.order.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.order.dto.CreateOrderRequest;
import com.cskaoyan.order.dto.CreateOrderResponse;
import com.cskaoyan.order.dto.OrderListRequest;
import com.cskaoyan.order.service.OrderCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @since 2022/07/08 22:42
 * @author Gzy
 */

@RestController
public class OrderCoreController {

    @Autowired
    OrderCoreService orderCoreService;

    /**
     * @description: 创建订单
     * @parameter: []
     * @return: com.cskaoyan.mall.commons.result.ResponseData
     * @hor: 帅关
     * @createTime: 2022/7/8 22:44
     */
    @PostMapping("shopping/order")
    public ResponseData createOrder(@RequestBody CreateOrderRequest request){
        CreateOrderResponse order = orderCoreService.createOrder(request);
        return new ResponseUtil().setData(order.getOrderId());
    }

}
