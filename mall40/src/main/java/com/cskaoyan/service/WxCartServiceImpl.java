package com.cskaoyan.service;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsProduct;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexAllNumberVo;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;
import com.cskaoyan.mapper.*;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @since 2022/06/28 19:23
 * @author Gzy
 */

@Service
public class WxCartServiceImpl implements WxCartService{

    @Autowired
    HttpSession session;
    @Autowired
    MarketCartMapper cartMapper;
    @Autowired
    MarketUserMapper userMapper;
    @Autowired
    MarketGoodsMapper goodsMapper;
    @Autowired
    MarketGoodsProductMapper productMapper;

    /**
     * @description: 添加购物车
     * @parameter: [cart]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/28 21:49
     */
    @Override
    public int cartAddNewGoods(MarketCart cart) {

        // String username = (String) session.getAttribute("username");
        // 查询userId
        // Integer id = userMapper.selectUserIdByName(username);
        // 查询good和product表
        MarketCart cartPo = productMapper.selectPartDataByPrimaryKey(cart.getProductId());
        Date date = new Date();
        // 对象封装
        cartPo.setUserId(1)
                .setAddTime(date)
                .setUpdateTime(date)
                .setDeleted(false)
                .setGoodsId(cart.getGoodsId())
                .setNumber(cart.getNumber())
                .setProductId(cart.getProductId());
        cartMapper.insertSelective(cartPo);

        // 获取购物车中的商品数量
        return cartMapper.selectAllNumber();
    }

    /**
     * @description: 查询购物车所有商品
     * @parameter: []
     * @return: com.cskaoyan.bean.vo.wxCart.WxCartIndexVo
     * @author: 帅关
     * @createTime: 2022/6/28 22:19
     */
    @Override
    public WxCartIndexVo queryAllCartGoods() {
        // 获取所有购物车商品
        List<MarketCart> marketCarts = cartMapper.selectByExample(null);

        // 计算商品价格和数量
        int goodsCount = 0;
        int checkedGoodsCount = 0;
        int goodsAmount = 0;
        int checkedGoodsAmount = 0;

        Short number;
        float price;
        for (MarketCart marketCart : marketCarts) {
            number = marketCart.getNumber();
            goodsCount += number;
            price = marketCart.getPrice().floatValue();
            goodsAmount += number * price;
            if(marketCart.getChecked()){
                checkedGoodsAmount += number * price;
                checkedGoodsCount += goodsCount;
            }
        }
        // 封装对象
        WxCartIndexAllNumberVo cartTotal = new WxCartIndexAllNumberVo();
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount)
                .setCheckedGoodsCount(checkedGoodsCount)
                .setGoodsAmount(goodsAmount)
                .setGoodsCount(goodsCount);


        return new WxCartIndexVo()
                .setCartList(marketCarts)
                .setCartTotal(cartTotal);
    }
}
