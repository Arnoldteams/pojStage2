package com.cskaoyan.shopping;

import com.cskaoyan.shopping.dto.AllProductCateRequest;
import com.cskaoyan.shopping.dto.AllProductCateResponse;
import com.cskaoyan.shopping.service.IProductCateService;
import com.cskaoyan.shopping.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 文陶
 * @createTime: 2022年07月10日 16:59:16
 * @version:
 * @Description: 测试
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CateTest {
    @Autowired
    IProductCateService cateService;

    @Test
    public void test(){
        AllProductCateRequest request = new AllProductCateRequest();
        request.setSort("1");
        AllProductCateResponse allProductCate = cateService.getAllProductCate(request);
        System.out.println(allProductCate.toString());
    }

//    @Autowired
//    IProductService productService;
//    public void test2(){
//        productService.getAllProduct()
//    }
}
