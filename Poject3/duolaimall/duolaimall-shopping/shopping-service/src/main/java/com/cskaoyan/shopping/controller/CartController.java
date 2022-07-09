package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cskaoyan.shopping.dto.CheckAllItemRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 于艳帆
 * @createTime: 2022年07月09日 13:34:46
 * @version:
 * @Description:
 */
@RestController
@RequestMapping("/shopping")
public class CartController {
    @Autowired
    ICartService cartService;

    @RequestMapping("/items")
    public ResponseData checkAllCartItem(@RequestBody CheckAllItemRequest request) {
        return new ResponseUtil().setData(null);
    }

    @PostMapping("carts")
    public ResponseData addProductToCart(@RequestBody AddCartRequest request) {

        AddCartResponse response = cartService.addToCart(request);
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            // 执行成功
            return new ResponseUtil().getResponseData();
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
