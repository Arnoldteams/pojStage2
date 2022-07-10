package com.cskaoyan.shopping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.dto.ProductDto;
import com.cskaoyan.shopping.converter.ProductConverter;
import com.cskaoyan.shopping.dal.entitys.Item;
import com.cskaoyan.shopping.dal.persistence.ItemMapper;
import com.cskaoyan.shopping.dto.CartProductDto;
import com.cskaoyan.shopping.dto.UpdateCartNumRequest;
import com.cskaoyan.shopping.dto.UpdateCartNumResponse;
import com.cskaoyan.shopping.form.CartForm;
import com.cskaoyan.shopping.service.ICartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 代码测试
 * @author xyg2597@163.com
 * @since 2022/07/09 16:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SparkxShoppingTest {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    ICartService iCartService;

    @Test
    public void testRedis(){

//        {"userId":"509","productId":100053001,"productNum":1,"checked":true}
        RMap<Long, CartProductDto> map = redissonClient.getMap("User509");
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setProductId((long)100023501);

        map.put(cartProductDto.getProductId(),cartProductDto);

        CartForm cartForm = JSON.parseObject("{\"userId\":\"509\",\"productId\":100053001,\"productNum\":1,\"checked\":true}", CartForm.class);
        UpdateCartNumRequest request = new UpdateCartNumRequest();
        request.setChecked(cartForm.getChecked());
        request.setItemId(cartForm.getProductId());
        request.setNum(cartForm.getProductNum());
        request.setUserId(cartForm.getUserId());
        UpdateCartNumResponse response = iCartService.updateCartNum(request);

        CartProductDto cartProductDto1 = map.get(cartProductDto.getProductId());
        System.out.println(cartProductDto1);


    }
}
