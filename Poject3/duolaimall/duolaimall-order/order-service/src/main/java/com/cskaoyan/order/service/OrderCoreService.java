package com.cskaoyan.order.service;


import com.cskaoyan.mall.order.dto.PayOrderSuccessRequest;
import com.cskaoyan.mall.order.dto.PayOrderSuccessResponse;
import com.cskaoyan.order.dto.*;

/**
 * 订单相关业务
 */
public interface OrderCoreService {

    /**
     * 创建订单
     * @param request
     * @return
     */
    CreateOrderResponse createOrder(CreateOrderRequest request);

    /**
     * 取消订单
     * @param request
     * @return
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest request);


    /**
     * 删除订单
     * @param request
     * @return
     */
    DeleteOrderResponse deleteOrder(DeleteOrderRequest request);

}
