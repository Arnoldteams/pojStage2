package com.cskaoyan.order;

import com.cskaoyan.order.api.ProductApi;
import com.cskaoyan.order.dal.entitys.OrderItem;
import com.cskaoyan.order.dal.entitys.Stock;
import com.cskaoyan.order.dal.persistence.OrderItemMapper;
import com.cskaoyan.order.dal.persistence.StockMapper;
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
}
