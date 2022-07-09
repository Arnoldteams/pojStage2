package com.cskaoyan.order.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.order.dto.OrderDetailRequest;
import com.cskaoyan.order.dto.OrderDetailResponse;
import com.cskaoyan.order.dto.OrderListRequest;
import com.cskaoyan.order.service.OrderQueryService;
import com.google.j2objc.annotations.AutoreleasePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @since 2022/07/09 11:11
 * @author Gzy
 */

@RestController
public class OrderQueryController {

    @Autowired
    OrderQueryService queryService;

    @GetMapping("shopping/order")
    public ResponseData queryAllOrder(@RequestBody OrderListRequest request){
        OrderDetailResponse orderDetailResponse = queryService.orderDetail(new OrderDetailRequest());
        return new ResponseUtil().setData(orderDetailResponse);
    }
}
