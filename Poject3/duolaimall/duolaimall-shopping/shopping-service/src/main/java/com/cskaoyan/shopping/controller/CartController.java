package com.cskaoyan.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.HttpServletRequest;

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

    /**
     * @author: 于艳帆
     * @createTime: 2022-07-09 17:26:20
     * @description: 加入购物车
     * @param: request - [AddCartRequest]
     * @return: com.cskaoyan.mall.commons.result.ResponseData
     */
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

    /**
     * @author: 于艳帆
     * @createTime: 2022-07-09 17:26:45
     * @description: 显示购物车列表
     * @param: request - [HttpServletRequest]
     * @return: com.cskaoyan.mall.commons.result.ResponseData
     */
    @GetMapping("carts")
    public ResponseData showCarts(HttpServletRequest request) {

        // 拿到当前用户id
        String userInfo = request.getHeader("user_info");
        JSONObject object = JSON.parseObject(userInfo);
        Long uid = Long.parseLong(object.get("uid").toString());

        CartListByIdRequest idRequest = new CartListByIdRequest();
        idRequest.setUserId(uid);
        CartListByIdResponse response = cartService.getCartListById(idRequest);
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            // 执行成功
            return new ResponseUtil().setData(response.getCartProductDtos());
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
