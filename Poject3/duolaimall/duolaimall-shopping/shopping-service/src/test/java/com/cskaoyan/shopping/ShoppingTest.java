package com.cskaoyan.shopping;

import com.cskaoyan.shopping.dal.entitys.Item;
import com.cskaoyan.shopping.dal.persistence.ItemMapper;
import com.cskaoyan.shopping.dto.CartListByIdRequest;
import com.cskaoyan.shopping.dto.CartListByIdResponse;
import com.cskaoyan.shopping.dto.CartProductDto;
import com.cskaoyan.shopping.service.ICartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShoppingTest {

    @Autowired
    ItemMapper itemMapper;


    @Autowired
    ICartService iCartService;

    @Test
    public void testGetCartListById(){
        CartListByIdRequest idRequest = new CartListByIdRequest();
        idRequest.setUserId(Long.parseLong("73"));
        CartListByIdResponse cartListById = iCartService.getCartListById(idRequest);
        for (CartProductDto cartProductDto : cartListById.getCartProductDtos()) {
            System.out.println(cartProductDto);
        }


    }

    // 测试数据库
    @Test
    public void testItem() {
        Item item = itemMapper.selectByPrimaryKey(100046401);
        System.out.println(item);
    }

//    // 测试服务业务
//    @Autowired
//    IProductService productService;
    public void testService() {

    }

//    @Autowired
//    JavaMailSender javaMailSender;
    // 测试服务的Controller
    @Test
    public void testController() {
        RestTemplate restTemplate = new RestTemplate();
        // 发送http请求测试
        String url = "http://localhost:8888";
        ResponseEntity<Object> forEntity = restTemplate.getForEntity(url, Object.class);
    }



}
