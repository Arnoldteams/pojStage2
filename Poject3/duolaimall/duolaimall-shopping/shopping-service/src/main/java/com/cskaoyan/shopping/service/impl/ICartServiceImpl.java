package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.mall.dto.ClearCartItemRequest;
import com.cskaoyan.mall.dto.ClearCartItemResponse;
import com.cskaoyan.shopping.converter.CartItemConverter;
import com.cskaoyan.shopping.dal.entitys.Item;
import com.cskaoyan.shopping.dal.persistence.ItemCatMapper;
import com.cskaoyan.shopping.dal.persistence.ItemMapper;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.service.ICartService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Gzy
 * @since 2022/07/09 14:28
 */

@Service
public class ICartServiceImpl implements ICartService {
    @Autowired
    ItemCatMapper catMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public CartListByIdResponse getCartListById(CartListByIdRequest request) {
        return null;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-07-09 15:39:14
     * @description: 根据请求参数添加商品到购物车 redis - cart【hash】
     * @param: request - [AddCartRequest]
     * @return: com.cskaoyan.shopping.dto.AddCartResponse
     */
    @Override
    public AddCartResponse addToCart(AddCartRequest request) {
        AddCartResponse response = new AddCartResponse();

        try {
            // 参数校验
            request.requestCheck();

            // 根据商品id 查出item
            Item item = itemMapper.selectByPrimaryKey(request.getProductId());

            // item -> cartProductDto
            CartProductDto cartProductDto = CartItemConverter.item2Dto(item);
            cartProductDto.setChecked("true");
            cartProductDto.setProductNum(request.getProductNum());

            // 加入购物车，写入redis
            String userCartName = "User"+request.getUserId();
            Long productId = request.getProductId();
            Long num = null;
            RMap<Long, CartProductDto> carts = redissonClient.getMap(userCartName);

            // 判断购物车是否存在商品，存在就 +num，不存在就添加商品
            if (carts.get(productId)!=null){
                CartProductDto cartProductDto1 = carts.get(productId);
                num = cartProductDto1.getProductNum() + request.getProductNum();
                cartProductDto1.setProductNum(num);
                carts.put(request.getProductId(),cartProductDto1);
            }else {
                carts.put(request.getProductId(),cartProductDto);
            }


            // 执行成功响应
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());

            // test
            // for (Map.Entry<Long, CartProductDto> longCartProductDtoEntry : carts.entrySet()) {
            //     System.out.println(longCartProductDtoEntry);
            // }


        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        return response;
    }

    @Override
    public UpdateCartNumResponse updateCartNum(UpdateCartNumRequest request) {
        return null;
    }

    @Override
    public CheckAllItemResponse checkAllCartItem(CheckAllItemRequest request) {
        return null;
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
