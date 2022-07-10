package com.cskaoyan.order.service.impl;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.order.converter.OrderConverter;
import com.cskaoyan.order.dal.entitys.Order;
import com.cskaoyan.order.dal.entitys.OrderItem;
import com.cskaoyan.order.dal.entitys.OrderShipping;
import com.cskaoyan.order.dal.persistence.OrderItemMapper;
import com.cskaoyan.order.dal.persistence.OrderMapper;
import com.cskaoyan.order.dal.persistence.OrderShippingMapper;
import com.cskaoyan.order.dto.*;
import com.cskaoyan.order.service.OrderQueryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *
 * @since 2022/07/09 11:13
 * @author Gzy
 */

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper itemMapper;
    @Autowired
    OrderShippingMapper shippingMapper;
    @Autowired
    OrderConverter converter;

    /**
     * @description: 查询用户订单
     * @parameter: [request]
     * @return: com.cskaoyan.order.dto.OrderListResponse
     * @author: 帅关
     * @createTime: 2022/7/9 11:29
     */
    @Override
    public OrderListResponse orderList(OrderListRequest request) {
        // 参数校验

        OrderListResponse response = new OrderListResponse();

        try {
            request.requestCheck();
            PageHelper.startPage(request.getPage(), request.getSize(), request.getSort());
            // 获取该用户的所有订单
            Example example = new Example(Order.class);
            example.createCriteria().andEqualTo("userId", request.getUserId());
            List<Order> orders = orderMapper.selectByExample(example);
            List<OrderDetailInfo> orderDetailInfos = converter.order2detail(orders);

            for (OrderDetailInfo orderDetailInfo : orderDetailInfos) {
                // 获取每个订单下的所有商品信息
                Example exam = new Example(OrderItem.class);
                exam.createCriteria().andEqualTo("orderId", orderDetailInfo.getOrderId());
                List<OrderItem> orderItems = itemMapper.selectByExample(exam);
                List<OrderItemDto> orderItemDtos = converter.item2dto(orderItems);
                orderDetailInfo.setOrderItemDto(orderItemDtos);

                // 获取每个订单下的地址信息
                Example shippingExam = new Example(OrderShipping.class);
                shippingExam.createCriteria().andEqualTo("orderId", orderDetailInfo.getOrderId());
                OrderShipping orderShippings = shippingMapper.selectOneByExample(shippingExam);
                OrderShippingDto orderShippingDto = converter.shipping2dto(orderShippings);
                orderDetailInfo.setOrderShippingDto(orderShippingDto);
            }

            // 分页处理
            PageInfo<OrderDetailInfo> pageInfo = new PageInfo<>(orderDetailInfos);
            int size = pageInfo.getSize();
            response.setDetailInfoList(pageInfo.getList());
            response.setTotal((long) size);
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public OrderDetailResponse orderDetail(OrderDetailRequest request) {
        return null;
    }
}
