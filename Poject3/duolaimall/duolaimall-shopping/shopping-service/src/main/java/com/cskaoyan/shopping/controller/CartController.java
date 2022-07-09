package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.form.CartForm;
import com.cskaoyan.shopping.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CheckAllItemResponse checkAllCartItem(@RequestBody CheckAllItemRequest request) {
        return cartService.checkAllCartItem(request);
    }

    /**
     * 更新购物车的数据
     *
     * @param cartForm
     * @return com.cskaoyan.mall.commons.result.ResponseData
     * @author xyg2597@163.com
     * @since 2022/07/09 17:11
     */
    @PutMapping("shopping/carts")
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
    @DeleteMapping("shopping/carts/{uid}/{pid}")
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


    @DeleteMapping("shopping/items/{uid}")
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

}
