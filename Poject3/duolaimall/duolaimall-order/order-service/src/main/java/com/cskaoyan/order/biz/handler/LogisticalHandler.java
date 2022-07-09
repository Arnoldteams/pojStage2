package com.cskaoyan.order.biz.handler;

import com.cskaoyan.mall.commons.exception.BizException;
import com.cskaoyan.order.biz.context.CreateOrderContext;
import com.cskaoyan.order.biz.context.TransHandlerContext;
import com.cskaoyan.mall.order.constant.OrderRetCode;
import com.cskaoyan.order.dal.entitys.OrderShipping;
import com.cskaoyan.order.dal.persistence.OrderShippingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 处理物流信息（商品寄送的地址）
 */
@Slf4j
@Component
public class LogisticalHandler extends AbstractTransHandler {

    @Autowired
    OrderShippingMapper orderShippingMapper;


    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        CreateOrderContext orderContext = (CreateOrderContext) context;
        if(orderContext.getOrderId() == null){
            throw new BizException("orderId不能为空！");
        }
        // 封装对象
        Date date = new Date();
        OrderShipping orderShipping = new OrderShipping();
        orderShipping.setOrderId(orderContext.getOrderId());
        orderShipping.setReceiverName(orderContext.getUserName());
        orderShipping.setReceiverAddress(orderContext.getStreetName());
        orderShipping.setReceiverPhone(orderContext.getTel());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);
        return true;
    }
}
