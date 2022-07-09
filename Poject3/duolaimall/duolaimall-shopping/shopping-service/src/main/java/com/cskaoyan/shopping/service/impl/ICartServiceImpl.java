package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.commons.exception.ExceptionUtil;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.mall.dto.ClearCartItemRequest;
import com.cskaoyan.mall.dto.ClearCartItemResponse;
import com.cskaoyan.mall.dto.ProductDetailDto;
import com.cskaoyan.shopping.dal.persistence.ItemCatMapper;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.service.ICartService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 *
 * @since 2022/07/09 14:28
 * @author Gzy
 */

@Service
public class ICartServiceImpl implements ICartService {
    @Autowired
    ItemCatMapper catMapper;

    @Autowired
    RedissonClient redissonClient;



    @Override
    public CartListByIdResponse getCartListById(CartListByIdRequest request) {
        return null;
    }

    @Override
    public AddCartResponse addToCart(AddCartRequest request) {
        return null;
    }

    /**
     * 更新商品数据
     * @param request
     * @return com.cskaoyan.shopping.dto.UpdateCartNumResponse
     * @author xyg2597@163.com
     * @since 2022/07/09 15:35
     */
    @Override
    public UpdateCartNumResponse updateCartNum(UpdateCartNumRequest request) {

        UpdateCartNumResponse response = new UpdateCartNumResponse();
        try {
//            request校验
            request.requestCheck();

            String cartId = "User" + request.getUserId();
            RMap<Long, CartProductDto> map = redissonClient.getMap(cartId);

            CartProductDto cartProductDto = map.get(request.getItemId());
            cartProductDto.setProductNum((long)request.getNum());
            map.put(request.getItemId(),cartProductDto);

            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }

        return response;
    }

    @Override
    public CheckAllItemResponse checkAllCartItem(CheckAllItemRequest request) {
        return null;
    }

    /**
     * 删除指定购物车商品
     * @param request
     * @return com.cskaoyan.shopping.dto.DeleteCartItemResponse
     * @author xyg2597@163.com
     * @since 2022/07/09 17:22
     */
    @Override
    public DeleteCartItemResponse deleteCartItem(DeleteCartItemRequest request) {
        DeleteCartItemResponse response = new DeleteCartItemResponse();

        try {
            String cart = "User" + request.getUserId();
            RMap<Long, CartProductDto> map = redissonClient.getMap(cart);
            map.remove(request.getItemId());
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }

        return response;
    }

    /**
     * 删除选中的所有商品
     * @param request
     * @return com.cskaoyan.shopping.dto.DeleteCheckedItemResposne
     * @author xyg2597@163.com
     * @since 2022/07/09 17:30
     */
    @Override
    public DeleteCheckedItemResposne deleteCheckedItem(DeleteCheckedItemRequest request) {

        DeleteCheckedItemResposne response = new DeleteCheckedItemResposne();

        try {
//            获得用户id，并得到存储在redis中的商品列表
            String cart = "User" + request.getUserId();
            RMap<Long, CartProductDto> map = redissonClient.getMap(cart);

//            对该用户的购物车商品进行遍历
            Set<Map.Entry<Long, CartProductDto>> entrySet = map.entrySet();
            for (Map.Entry<Long, CartProductDto> entry : entrySet) {
//                如果选中，进行删除
                if ("true".equals(entry.getValue().getChecked())) {
                    map.remove(entry.getKey());
                }
            }

            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }


        return response;
    }

    @Override
    public ClearCartItemResponse clearCartItemByUserID(ClearCartItemRequest request) {
        return null;
    }
}
