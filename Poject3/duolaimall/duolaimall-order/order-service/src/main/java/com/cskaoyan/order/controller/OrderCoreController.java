package com.cskaoyan.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.order.dto.CreateOrderRequest;
import com.cskaoyan.order.dto.CreateOrderResponse;
import com.cskaoyan.order.service.OrderCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @since 2022/07/08 22:42
 * @author Gzy
 */

@RestController
@RequestMapping("/shopping")
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
    @PostMapping("/order")
    public ResponseData createOrder(@RequestBody CreateOrderRequest request, HttpServletRequest httpServletRequest){
        // String userInfo = httpServletRequest.getHeader("user_info");
        // JSONObject jsonObject = JSON.parseObject(userInfo);
        // long uid = Long.parseLong(jsonObject.get("uid").toString());
        // request.setUserId(uid);
        request.setUserId((long) 71);
        CreateOrderResponse order = orderCoreService.createOrder(request);
        return new ResponseUtil().setData(order.getOrderId());
    }

}
