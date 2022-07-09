package com.cskaoyan.order.biz.handler;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.BizException;
import com.cskaoyan.order.biz.context.CreateOrderContext;
import com.cskaoyan.order.biz.context.TransHandlerContext;
import com.cskaoyan.order.dal.entitys.Stock;
import com.cskaoyan.order.dal.persistence.OrderMapper;
import com.cskaoyan.order.dal.persistence.StockMapper;
import com.cskaoyan.order.dto.CartProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ValidateHandler extends AbstractTransHandler {


    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        return true;
    }
}
