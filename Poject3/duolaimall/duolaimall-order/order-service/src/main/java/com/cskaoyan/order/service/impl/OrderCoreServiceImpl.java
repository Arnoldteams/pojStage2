package com.cskaoyan.order.service.impl;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.order.constant.OrderRetCode;
import com.cskaoyan.order.biz.TransOutboundInvoker;
import com.cskaoyan.order.biz.context.AbsTransHandlerContext;
import com.cskaoyan.order.biz.factory.OrderProcessPipelineFactory;
import com.cskaoyan.order.constant.OrderConstants;
import com.cskaoyan.order.dal.entitys.Order;
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
import tk.mybatis.mapper.entity.Example;

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
			// 参数校验
			request.requestCheck();

			//创建pipeline对象
			TransOutboundInvoker invoker = orderProcessPipelineFactory.build(request);

			//启动pipeline
			invoker.start(); //启动流程（pipeline来处理）

			//获取处理结果
			AbsTransHandlerContext context = invoker.getContext();

			//把处理结果转换为response
			response = (CreateOrderResponse) context.getConvert().convertCtx2Respond(context);

			response.setMsg(SysRetCodeConstants.SUCCESS.getCode());
			response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());

		} catch (Exception e) {
			log.error("OrderCoreServiceImpl.createOrder Occur Exception :" + e);
			response.setCode(OrderRetCode.INIT_ORDER_EXCEPTION.getCode());
			response.setMsg(OrderRetCode.INIT_ORDER_EXCEPTION.getMessage());
			ExceptionProcessorUtils.wrapperHandlerException(response, e);
		}
		return response;
	}

	/**
	 * @author Sssd
	 */
	@Override
	public CancelOrderResponse cancelOrder(CancelOrderRequest request) {
		// 创建返回值对象
		CancelOrderResponse response = new CancelOrderResponse();

		try {
			// 根据 id 搜索 order 信息
			Order order = orderMapper.selectByPrimaryKey(request.getOrderId());
			// 将状态定义为 ORDER_STATUS_TRANSACTION_CANCEL(7)
			order.setStatus(OrderConstants.ORDER_STATUS_TRANSACTION_CANCEL);
			// 修改数据库数据
			orderMapper.updateByPrimaryKeySelective(order);

			// 将数据赋值给 response
			response.setCode(OrderRetCode.SUCCESS.getCode());
			response.setMsg(OrderRetCode.SUCCESS.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionProcessorUtils.wrapperHandlerException(response, e);
		}

		return response;
	}

	/**
	 * Sssd
	 */
	@Override
	public DeleteOrderResponse deleteOrder(DeleteOrderRequest request) {
		// 创建返回值对象
		DeleteOrderResponse response = new DeleteOrderResponse();

		try {
			// 根据主键删除 order 对象
			int i = orderMapper.deleteByPrimaryKey(request.getOrderId());
			if (i != 1) {
				throw new Exception();
			}
			response.setCode(OrderRetCode.SUCCESS.getCode());
			response.setMsg(OrderRetCode.SUCCESS.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionProcessorUtils.wrapperHandlerException(response, e);
		}

		return response;
	}

}
