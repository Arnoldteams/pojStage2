package com.cskaoyan.order;

import com.cskaoyan.order.api.ProductApi;
import com.cskaoyan.order.dal.entitys.OrderItem;
import com.cskaoyan.order.dal.entitys.Stock;
import com.cskaoyan.order.dal.persistence.OrderItemMapper;
import com.cskaoyan.order.dal.persistence.StockMapper;
import com.cskaoyan.order.dto.CancelOrderRequest;
import com.cskaoyan.order.dto.DeleteOrderRequest;
import com.cskaoyan.order.dto.OrderDetailRequest;
import com.cskaoyan.order.dto.OrderDetailResponse;
import com.cskaoyan.order.service.OrderCoreService;
import com.cskaoyan.order.service.OrderQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    OrderQueryService orderQueryService;

    @Autowired
    OrderCoreService orderCoreService;


    @Autowired
    ProductApi productApi;


    @Autowired
    StockMapper stockMapper;

    @Autowired
    OrderItemMapper itemMapper;

    @Test
    public void testCallRemoteService() {
        Stock stock = stockMapper.selectByPrimaryKey(111);
    }

    @Test
    public void testCallOrderItem() {
        List<OrderItem> orderItems = itemMapper.selectAll();
        System.out.println(orderItems);

        Timestamp create = orderItems.get(0).getCreate();
        LocalDateTime localDateTime = create.toLocalDateTime();
    }

    /**
     * Sssd
     */
    @Test
    public void testOrderDetail() {
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setOrderId("19081913521928018");
        OrderDetailResponse response = orderQueryService.orderDetail(orderDetailRequest);
    }

    /**
     * Sssd
     */
    @Test
    public void testOrderCancel() {
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setOrderId("20041914021310366");
        orderCoreService.cancelOrder(cancelOrderRequest);
    }

    /**
     * Sssd
     */
    @Test
    public void testOrderDelete() {
        DeleteOrderRequest request = new DeleteOrderRequest();
        request.setOrderId("20041914021310366");
        orderCoreService.deleteOrder(request);
    }
}
