package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.MarketCartExample;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.WxCartCheckBo;
import com.cskaoyan.bean.vo.wxCart.WxCartCheckedVo;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexAllNumberVo;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;
import com.cskaoyan.mapper.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @since 2022/06/28 19:23
 * @author Gzy
 */

@Service
public class WxCartServiceImpl implements WxCartService {

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
    @Autowired
    MarketAddressMapper addressMapper;
    @Autowired
    MarketSystemMapper systemMapper;
    @Autowired
    MarketCouponMapper couponMapper;


    /**
     * @description: 添加购物车
     * @parameter: [cart]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/28 21:49
     */
    @Override
    public int cartAddNewGoodsAndReturnCount(MarketCart cart) {
        addGoodsToCart(cart);
        // 获取购物车中的商品数量
        return cartMapper.selectAllNumber();
    }

    /**
     * @description: 将商品加入购物车
     * @parameter: [cart]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/29 12:23
     */
    private Integer addGoodsToCart(MarketCart cart) {
        // 获取userId
        Integer userId = getUserId();

        // 查询good和product表
        MarketCart cartPo = productMapper.selectPartDataByPrimaryKey(cart.getProductId());

        // 设置更新时间和状态位
        Date date = new Date();

        // 购物车中存在同款商品，则商品数量合并
        Integer cartId;
        if ((cartId = cartMapper.selectIdByUserIdAndProductId(userId, cart.getProductId())) != null) {
            cartMapper.updateCartInfo(cartId, cart.getNumber(), date);
            return cartId;
        }
        // 不存在同款商品，新添一条记录
        // 对象封装
        cartPo.setUserId(userId)
                .setUpdateTime(date)
                .setNumber(cart.getNumber())
                .setDeleted(false)
                .setAddTime(date)
                .setGoodsId(cart.getGoodsId())
                .setProductId(cart.getProductId());
        cartMapper.insertSelective(cartPo);

        return cartPo.getId();
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
        MarketCartExample example = new MarketCartExample();
        Integer userId = getUserId();
        example.createCriteria()
                .andDeletedEqualTo(false)
                .andUserIdEqualTo(userId);

        List<MarketCart> marketCarts = cartMapper.selectByExample(example);


        // 封装对象
        WxCartIndexAllNumberVo cartTotal = new WxCartIndexAllNumberVo();
        countTotalGoodsInfo(cartTotal,marketCarts);
        return new WxCartIndexVo()
                .setCartList(marketCarts)
                .setCartTotal(cartTotal);
    }

    /**
     * @description: 获取指定商品的总数量，价格，选定商品的总数量，价格
     * @parameter: [cartTotal, marketCarts]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/29 17:42
     */
    private void countTotalGoodsInfo(WxCartIndexAllNumberVo cartTotal,List<MarketCart> marketCarts){
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
            if (marketCart.getChecked()) {
                checkedGoodsAmount += number * price;
                checkedGoodsCount += number;
            }
        }
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount)
                .setCheckedGoodsCount(checkedGoodsCount)
                .setGoodsAmount(goodsAmount)
                .setGoodsCount(goodsCount);
    }


    /**
     * @description: 改变购物车中商品的选中状态
     * @parameter: []
     * @return: com.cskaoyan.bean.vo.wxCart.WxCartIndexVo
     * @author: 帅关
     * @createTime: 2022/6/29 11:00
     */
    @Override
    public WxCartIndexVo updateGoodsChecked(Map map) {
        List<Integer> productIds = (List<Integer>) map.get("productIds");
        Integer checked = (Integer) map.get("isChecked");

        // 获取用户Id
        Integer userId = getUserId();

        cartMapper.updateCartGoodsChecked(userId, productIds, checked);
        return queryAllCartGoods();
    }

    /**
     * @description: 添加订单并获取订单编号
     * @parameter: [cart]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/29 12:40
     */
    @Override
    public int cartAddNewGoodsAndReturnCartId(MarketCart cart) {
        return addGoodsToCart(cart);
    }

    /**
     * @description: 删除购物车商品
     * @parameter: []
     * @return: com.cskaoyan.bean.vo.wxCart.WxCartIndexVo
     * @author: 帅关
     * @createTime: 2022/6/29 13:56
     */
    @Override
    public WxCartIndexVo cartDeleteGoodsByProductsId(Map map) {
        Integer userId = getUserId();
        List<Integer> productIds = (List<Integer>) map.get("productIds");
        cartMapper.updateCartGoodsDeleted(userId,productIds);
        return queryAllCartGoods();
    }

    /**
     * @description: 更新购物车商品数量
     * @parameter: [cart]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/29 14:38
     */
    @Override
    @Async
    public void updateGoodsNumberByCartId(MarketCart cart) {
        cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public Integer queryCartGoodsCount() {
        Integer userId = getUserId();
        Integer count = cartMapper.selectCartGoodsCount(userId);
        return count;
    }

    /**
     * @description: 下单前确认商品信息
     * @parameter: []
     * @return: com.cskaoyan.bean.vo.wxCart.WxCartCheckedVo
     * @author: 帅关
     * @createTime: 2022/6/29 17:01
     */
    @Override
    public WxCartCheckedVo queryCartCheckInfo(WxCartCheckBo cartCheckBo) {
        // 获得用户信息
        Integer userId = getUserId();
        MarketAddress address = addressMapper.selectAddressInfoByUserId(userId);

        // 订单商品信息
        MarketCartExample example = new MarketCartExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andDeletedEqualTo(false)
                .andCheckedEqualTo(true);
        List<MarketCart> marketCarts = cartMapper.selectByExample(example);

        WxCartCheckedVo wxCartCheckedVo = new WxCartCheckedVo();
        wxCartCheckedVo.setCheckedGoodsList(marketCarts);
        wxCartCheckedVo.setCheckedAddress(address);
        if(address != null){
            wxCartCheckedVo.setAddressId(address.getId());
        }

        wxCartCheckedVo.setAvailableCouponLength(10);

        // 获取运费信息
        WxCartIndexAllNumberVo cartTotal = new WxCartIndexAllNumberVo();
        countTotalGoodsInfo(cartTotal,marketCarts);
        int goodsPrice = (int) cartTotal.getCheckedGoodsAmount();
        wxCartCheckedVo.setGoodsTotalPrice(goodsPrice);
        Integer freightPrice = orderFreightPrice(goodsPrice);
        Integer orderPrice = goodsPrice + freightPrice;
        wxCartCheckedVo.setFreightPrice(freightPrice);
        wxCartCheckedVo.setOrderTotalPrice(goodsPrice+freightPrice);

        // 获得优惠信息
        Integer couponPrice = 0;
        Integer actualPrice = orderPrice;
        if(cartCheckBo.getCouponId() > 0 && cartCheckBo.getUserCouponId() > 0){
            wxCartCheckedVo.setUserCouponId(cartCheckBo.getUserCouponId());
            wxCartCheckedVo.setCouponId(cartCheckBo.getCouponId());
            couponPrice = couponMapper.selectDiscountByCouponId(cartCheckBo.getCouponId());
            wxCartCheckedVo.setCouponPrice(couponPrice);

        }else{
            wxCartCheckedVo.setUserCouponId(-1);
            wxCartCheckedVo.setCouponId(-1);
        }

        // 获取优惠券信息


        actualPrice = orderPrice - couponPrice;
        wxCartCheckedVo.setActualPrice(actualPrice);
        return wxCartCheckedVo;
    }


    /**
     * @description: 获取商品运费信息
     * @parameter: [orderPrice]
     * @return: java.lang.Integer
     * @author: 帅关
     * @createTime: 2022/6/29 20:12
     */
    private Integer orderFreightPrice(Integer orderPrice){
        Integer minFreight = systemMapper.selectKeyValueByKeyName("market_express_freight_min");
        if(orderPrice > minFreight){
            return 0;
        }
        return systemMapper.selectKeyValueByKeyName("market_express_freight_value");
    }


    /**
     * @description: 获取用户id
     * @parameter: []
     * @return: java.lang.Integer
     * @author: 帅关
     * @createTime: 2022/6/29 11:16
     */
    private Integer getUserId() {

        // 获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipal();
        return user.getId();
    }
}
