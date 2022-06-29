package com.cskaoyan.service;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.bo.WxCartCheckBo;
import com.cskaoyan.bean.vo.wxCart.WxCartCheckedVo;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;

import java.util.List;
import java.util.Map;

public interface WxCartService {
    // 增加购物车并返回购物车中的商品数量
    int cartAddNewGoodsAndReturnCount(MarketCart cart);

    // 查询购物车商品
    WxCartIndexVo queryAllCartGoods();

    // 改变购物车选中状态
    WxCartIndexVo updateGoodsChecked(Map map);

    // 立即购买
    int cartAddNewGoodsAndReturnCartId(MarketCart cart);

    // 删除购物车商品
    WxCartIndexVo cartDeleteGoodsByProductsId(Map productIds);

    // 更新购物车商品数量
    void updateGoodsNumberByCartId(MarketCart cart);

    // 获取购物车商品数量
    Integer queryCartGoodsCount();


    WxCartCheckedVo queryCartCheckInfo(WxCartCheckBo cartCheckBo);
}
