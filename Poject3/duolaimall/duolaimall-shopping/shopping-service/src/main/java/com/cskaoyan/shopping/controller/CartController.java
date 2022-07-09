package com.cskaoyan.shopping.controller;

import com.cskaoyan.shopping.dto.CheckAllItemRequest;
import com.cskaoyan.shopping.dto.CheckAllItemResponse;
import com.cskaoyan.shopping.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 于艳帆
 * @createTime: 2022年07月09日 13:34:46
 * @version:
 * @Description:
 */
@RestController
public class CartController {
    @Autowired
    ICartService cartService;

    @RequestMapping("shopping/items")
    public CheckAllItemResponse checkAllCartItem(@RequestBody CheckAllItemRequest request){
        return cartService.checkAllCartItem(request);
    }
}
