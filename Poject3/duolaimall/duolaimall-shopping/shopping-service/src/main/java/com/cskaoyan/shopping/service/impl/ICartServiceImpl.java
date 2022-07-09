package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.dto.ClearCartItemRequest;
import com.cskaoyan.mall.dto.ClearCartItemResponse;
import com.cskaoyan.shopping.constants.GlobalConstants;
import com.cskaoyan.shopping.dal.persistence.ItemCatMapper;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.service.ICartService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public UpdateCartNumResponse updateCartNum(UpdateCartNumRequest request) {
        return null;
    }

    @Override
    public CheckAllItemResponse checkAllCartItem(CheckAllItemRequest request) {
        request.requestCheck();
        String checked = request.getChecked();
        RMap<Long, List<CartProductDto>> map = redissonClient.getMap("carts");
        map.get(request.getUserId())
        .forEach(a -> a.setChecked(checked));
        return new CheckAllItemResponse();
    }

    @Override
    public DeleteCartItemResponse deleteCartItem(DeleteCartItemRequest request) {
        return null;
    }

    @Override
    public DeleteCheckedItemResposne deleteCheckedItem(DeleteCheckedItemRequest request) {
        return null;
    }

    @Override
    public ClearCartItemResponse clearCartItemByUserID(ClearCartItemRequest request) {
        return null;
    }
}
