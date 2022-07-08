package com.cskaoyan.order.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.order.biz.TransOutboundInvoker;
import com.cskaoyan.order.biz.context.AbsTransHandlerContext;
import com.cskaoyan.order.biz.factory.OrderProcessPipelineFactory;
import com.cskaoyan.order.dal.persistence.OrderItemMapper;
import com.cskaoyan.order.dal.persistence.OrderMapper;
import com.cskaoyan.order.dal.persistence.OrderShippingMapper;
import com.cskaoyan.order.dal.persistence.StockMapper;
import com.cskaoyan.order.dto.*;
import com.cskaoyan.order.service.OrderCoreService;
import com.cskaoyan.order.utils.GlobalIdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCoreServiceImpl implements OrderCoreService {


	@Autowired
    OrderMapper orderMapper;

	@Autowired
    OrderItemMapper orderItemMapper;

	@Autowired
    OrderShippingMapper orderShippingMapper;

	@Autowired
    OrderProcessPipelineFactory orderProcessPipelineFactory;

	@Autowired
    StockMapper stockMapper;


	/**
	 * 创建订单的处理流程
	 *
	 * @param request
	 * @return
	 */
	@Override
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		CreateOrderResponse response = new CreateOrderResponse();
		try {
			//创建pipeline对象
			TransOutboundInvoker invoker = orderProcessPipelineFactory.build(request);

			//启动pipeline
			invoker.start(); //启动流程（pipeline来处理）

			//获取处理结果
			AbsTransHandlerContext context = invoker.getContext();

			//把处理结果转换为response
			response = (CreateOrderResponse) context.getConvert().convertCtx2Respond(context);

		} catch (Exception e) {
			log.error("OrderCoreServiceImpl.createOrder Occur Exception :" + e);
			ExceptionProcessorUtils.wrapperHandlerException(response, e);
		}
		return response;
	}

	@Override
	public CancelOrderResponse cancelOrder(CancelOrderRequest request) {
		return null;
	}

	@Override
	public DeleteOrderResponse deleteOrder(DeleteOrderRequest request) {
		return null;
	}

}
