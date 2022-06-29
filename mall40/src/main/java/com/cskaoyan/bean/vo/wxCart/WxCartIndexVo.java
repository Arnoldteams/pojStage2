package com.cskaoyan.bean.vo.wxCart;

import com.cskaoyan.bean.MarketCart;

import java.util.List;

/**
 *
 * @since 2022/06/28 22:17
 * @author Gzy
 */


public class WxCartIndexVo {

    private WxCartIndexAllNumberVo cartTotal;
    private List<MarketCart> cartList;

    public WxCartIndexAllNumberVo getCartTotal() {
        return cartTotal;
    }

    public WxCartIndexVo setCartTotal(WxCartIndexAllNumberVo cartTotal) {
        this.cartTotal = cartTotal;
        return this;
    }

    public List<MarketCart> getCartList() {
        return cartList;
    }

    public WxCartIndexVo setCartList(List<MarketCart> cartList) {
        this.cartList = cartList;
        return this;
    }
}
