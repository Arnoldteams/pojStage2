package com.cskaoyan.order.service.impl;

import com.cskaoyan.order.dto.OrderDetailRequest;
import com.cskaoyan.order.dto.OrderDetailResponse;
import com.cskaoyan.order.dto.OrderListRequest;
import com.cskaoyan.order.dto.OrderListResponse;
import com.cskaoyan.order.service.OrderQueryService;
import org.springframework.stereotype.Service;

/**
 *
 * @since 2022/07/09 11:13
 * @author Gzy
 */

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    @Override
    public OrderListResponse orderList(OrderListRequest request) {
        return null;
    }

    @Override
    public OrderDetailResponse orderDetail(OrderDetailRequest request) {
        return null;
    }
}
