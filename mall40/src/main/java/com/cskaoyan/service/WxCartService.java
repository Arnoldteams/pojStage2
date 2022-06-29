package com.cskaoyan.service;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;

public interface WxCartService {
    // 增加购物车
    int cartAddNewGoods(MarketCart cart);

    // 查询购物车商品
    WxCartIndexVo queryAllCartGoods();
}
