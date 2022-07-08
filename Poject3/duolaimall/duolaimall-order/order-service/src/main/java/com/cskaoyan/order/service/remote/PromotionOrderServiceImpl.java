package com.cskaoyan.order.service.remote;

import com.cskaoyan.mall.order.dto.CreateSeckillOrderRequest;
import com.cskaoyan.mall.order.dto.CreateSeckillOrderResponse;
import com.cskaoyan.order.service.OrderPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionOrderServiceImpl{

    @Autowired
    OrderPromoService orderPromoService;

    @RequestMapping(value = "/rpc/promotion/order",method = RequestMethod.POST)
    public CreateSeckillOrderResponse createPromoOrder(@RequestBody CreateSeckillOrderRequest request) {
        return orderPromoService.createPromoOrder(request);
    }
}
