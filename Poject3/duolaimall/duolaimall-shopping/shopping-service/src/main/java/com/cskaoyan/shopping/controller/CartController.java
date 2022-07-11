package com.cskaoyan.shopping.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.*;

import com.cskaoyan.shopping.form.CartForm;
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
     * 更新购物车的数据
     *
     * @param cartForm
     * @return com.cskaoyan.mall.commons.result.ResponseData
     * @author xyg2597@163.com
     * @since 2022/07/09 17:11
     */
    @RequestMapping(value = "carts",method = RequestMethod.PUT)
    public ResponseData updateCartNum(@RequestBody CartForm cartForm) {
        UpdateCartNumRequest request = new UpdateCartNumRequest();
        request.setChecked(cartForm.getChecked());
        request.setItemId(cartForm.getProductId());
        request.setNum(cartForm.getProductNum());
        request.setUserId(cartForm.getUserId());

        UpdateCartNumResponse response = cartService.updateCartNum(request);
//        更新成功
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return new ResponseUtil<>().setData("成功");
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());

    }

    /**
     * 删除指定的购物车商品
     * @param uid 用户id
     * @param pid 商品id
     * @return com.cskaoyan.mall.commons.result.ResponseData
     * @author xyg2597@163.com
     * @since 2022/07/09 17:21
     */
    @RequestMapping(value = "carts/{uid}/{pid}",method = RequestMethod.DELETE)
    public ResponseData deleteCartItem(@PathVariable("uid") Long uid,
                                   @PathVariable("pid") Long pid) {

        DeleteCartItemRequest request = new DeleteCartItemRequest();
        request.setItemId(pid);
        request.setUserId(uid);

        DeleteCartItemResponse response = cartService.deleteCartItem(request);
//        删除成功
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return new ResponseUtil<>().setData("成功");
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());

    }


    /**
     * 删除多个购物车商品
     * @param uid 登录用户id
     * @return com.cskaoyan.mall.commons.result.ResponseData
     * @author xyg2597@163.com
     * @since 2022/07/11 8:55
     */
    @RequestMapping(value = "items/{uid}",method = RequestMethod.DELETE)
    public ResponseData deleteCheckCartItems(@PathVariable("uid") Long uid){
        DeleteCheckedItemRequest request = new DeleteCheckedItemRequest();
        request.setUserId(uid);

        DeleteCheckedItemResposne response = cartService.deleteCheckedItem(request);

//        删除成功
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return new ResponseUtil<>().setData("成功");
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());
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
