package com.cskaoyan.order.biz.handler;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.BizException;
import com.cskaoyan.mall.commons.util.NumberUtils;
import com.cskaoyan.mall.commons.util.UtilDate;
import com.cskaoyan.mall.dto.ProductDetailDto;
import com.cskaoyan.mall.dto.ProductDetailRequest;
import com.cskaoyan.mall.dto.ProductDetailResponse;
import com.cskaoyan.order.api.ProductApi;
import com.cskaoyan.order.biz.callback.SendEmailCallback;
import com.cskaoyan.order.biz.callback.TransCallback;
import com.cskaoyan.order.biz.context.CreateOrderContext;
import com.cskaoyan.order.biz.context.TransHandlerContext;
import com.cskaoyan.order.constant.OrderConstants;
import com.cskaoyan.mall.order.constant.OrderRetCode;
import com.cskaoyan.order.dal.entitys.Order;
import com.cskaoyan.order.dal.entitys.OrderItem;
import com.cskaoyan.order.dal.persistence.OrderItemMapper;
import com.cskaoyan.order.dal.persistence.OrderMapper;
import com.cskaoyan.order.dto.CartProductDto;
import com.cskaoyan.order.utils.GlobalIdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 初始化订单 生成订单
 */

@Slf4j
@Component
public class InitOrderHandler extends AbstractTransHandler {


    @Autowired
    SendEmailCallback sendEmailCallback;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    GlobalIdGeneratorUtil globalIdGeneratorUtil;

    @Autowired
    ProductApi productApi;



    private final String ORDER_GLOBAL_ID_CACHE_KEY="ORDER_ID";
    private final String ORDER_ITEM_GLOBAL_ID_CACHE_KEY="ORDER_ITEM_ID";
    @Override
    public TransCallback getTransCallback() {
        return sendEmailCallback;
    }

    @Override
    public boolean isAsync() {
        return false;
    }


    /**
     * @description: 生成订单信息，订单商品信息
     *                设置事务的传播状态为REQUIRES_NEW
     * @parameter: [context]
     * @return: boolean
     * @author: 帅关
     * @createTime: 2022/7/9 8:37
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean handle(TransHandlerContext context) {
        CreateOrderContext orderContext = (CreateOrderContext) context;
        // 生成订单ID，当前时间戳末六位+15位用户ID
        Long userId = orderContext.getUserId();
        String timeMillis = UtilDate.getOrderNum();
        String orderId = timeMillis + String.format("%0"+15+"d",userId);

        // odrderI存入context中，方便后续步骤处理
        ((CreateOrderContext) context).setOrderId(orderId);
        // 生成订单信息
        try{
            generateOrder(orderId,orderContext);
            generateOrderItems(orderId,orderContext);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(SysRetCodeConstants.DB_EXCEPTION.getCode(),
                    SysRetCodeConstants.DB_EXCEPTION.getMessage());
        }
        return true;
    }

    /**
     * @description: 生成订单商品信息
     * @parameter: [orderId, orderContext]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/7/9 9:31
     */
    private void generateOrderItems(String orderId, CreateOrderContext orderContext) {
        List<CartProductDto> cartProductDtoList = orderContext.getCartProductDtoList();
        for (CartProductDto cartProductDto : cartProductDtoList) {
            OrderItem orderItem = getOrderItemInfo(orderId,cartProductDto);
            int affectedRows;
            try{
                affectedRows = orderItemMapper.insert(orderItem);
            }catch (Exception e){
                e.printStackTrace();
                throw new BizException(OrderRetCode.DB_EXCEPTION.getCode(),
                        OrderRetCode.DB_EXCEPTION.getMessage());
            }
            if(affectedRows == 0){
                throw new BizException(OrderRetCode.DB_SAVE_EXCEPTION.getCode(),
                        OrderRetCode.DB_SAVE_EXCEPTION.getMessage());
            }
        }

    }

    private OrderItem getOrderItemInfo(String orderId,CartProductDto cartProductDto) {
        // 获取订单商品唯一ID
        String nextSeq;
        try{
            nextSeq = globalIdGeneratorUtil.getNextSeq(ORDER_ITEM_GLOBAL_ID_CACHE_KEY,1);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(OrderRetCode.SYSTEM_ERROR.getCode(),
                    OrderRetCode.SYSTEM_ERROR.getMessage());
        }
        // 获取商品信息
        Long productId = cartProductDto.getProductId();
        ProductDetailDto productDetailDto = getProductDetail(productId).getProductDetailDto();
        // 获取总价
        BigDecimal totalFee = cartProductDto.getSalePrice()
                .multiply(BigDecimal.valueOf(cartProductDto.getProductNum()));
        // 封装对象
        OrderItem orderItem = new OrderItem();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        orderItem.setId(nextSeq);
        orderItem.setItemId(productId);
        orderItem.setOrderId(orderId);
        orderItem.setNum(cartProductDto.getProductNum().intValue());
        orderItem.setTitle(productDetailDto.getSubTitle());
        orderItem.setPrice(productDetailDto.getSalePrice());
        orderItem.setTotalFee(totalFee);
        orderItem.setPicPath(productDetailDto.getProductImageBig());
        orderItem.setStatus(OrderConstants.STOCK_LOCKED);
        orderItem.setCreate(timestamp);
        return orderItem;
    }

    /**
     * @description: 获取商品详情信息
     * @parameter: [productId]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/7/9 9:47
     */
    private ProductDetailResponse getProductDetail(Long productId) {
        ProductDetailRequest productDetailRequest = new ProductDetailRequest();
        productDetailRequest.setId(productId);
        ProductDetailResponse productDetail;
        try{
            productDetail = productApi.getProductDetail(productDetailRequest);
        }catch (Exception e){
            throw new BizException(OrderRetCode.SYSTEM_ERROR.getCode(),
                    OrderRetCode.SYSTEM_ERROR.getMessage());
        }
        if(productDetail == null){
            throw new BizException(OrderRetCode.SYSTEM_ERROR.getCode(),
                    OrderRetCode.SYSTEM_ERROR.getMessage());
        }
        return productDetail;
    }

    /**
     * @description: 生成订单信息
     * @parameter: [context]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/7/9 9:22
     */
    private void generateOrder(String OrderId,CreateOrderContext context) {
        // 获取订单唯一ID
        String orderUniqueID = globalIdGeneratorUtil.getNextSeq(ORDER_GLOBAL_ID_CACHE_KEY, 1);

        // 封装数据
        Order order = new Order();
        // 获取当前时间
        Date date = new Date();
        order.setOrderId(OrderId);
        order.setPayment(context.getOrderTotal());
        order.setStatus(OrderConstants.ORDER_STATUS_INIT);
        order.setCreateTime(date);
        order.setCloseTime(new Date(date.getTime()+2*60*60*1000));
        order.setUserId(context.getUserId());
        order.setBuyerNick(context.getBuyerNickName());

        int affectedRows;
        try{
            affectedRows = orderMapper.insert(order);
        }catch (Exception e){
            throw new BizException(OrderRetCode.DB_EXCEPTION.getCode(),
                    OrderRetCode.DB_EXCEPTION.getMessage());
        }
        if(affectedRows == 0){
            throw new BizException(OrderRetCode.INIT_ORDER_EXCEPTION.getCode(),
                    OrderRetCode.INIT_ORDER_EXCEPTION.getMessage());
        }
    }

}
