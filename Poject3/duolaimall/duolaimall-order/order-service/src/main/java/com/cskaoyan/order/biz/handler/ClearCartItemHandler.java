package com.cskaoyan.order.biz.handler;

import com.cskaoyan.mall.dto.ClearCartItemRequest;
import com.cskaoyan.order.api.ProductApi;
import com.cskaoyan.order.biz.context.CreateOrderContext;
import com.cskaoyan.order.biz.context.TransHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ClearCartItemHandler extends AbstractTransHandler {

    // 删除购物车商品，调用商品服务
    @Autowired
    ProductApi api;


    //是否采用异步方式执行
    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        CreateOrderContext orderContext = (CreateOrderContext) context;
        ClearCartItemRequest clearCartItemRequest = new ClearCartItemRequest();
        clearCartItemRequest.setUserId(orderContext.getUserId());
        clearCartItemRequest.setProductIds(orderContext.getBuyProductIds());
        api.clearCartItemByUserID(clearCartItemRequest);
        return true;
    }
}
