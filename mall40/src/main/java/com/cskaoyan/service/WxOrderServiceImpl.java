package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.WxCartCheckBo;
import com.cskaoyan.bean.bo.wxOrder.*;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import com.cskaoyan.bean.vo.wxCart.WxCartCheckedVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailChildVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:47:16
 * @Description: 微信-订单
 */

@Service
@Transactional
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper wxOrderMapper;
    @Autowired
    MarketAddressMapper addressMapper;
    @Autowired
    WxCartService cartService;
    @Autowired
    MarketCartMapper cartMapper;
    @Autowired
    MarketOrderMapper orderMapper;
    @Autowired
    MarketCouponUserMapper couponUserMapper;
    @Autowired
    MarketOrderGoodsMapper orderGoodsMapper;
    @Autowired
    MarketGoodsProductMapper goodsProductMapper;

    /**
     * @author: ZY
     * @createTime: 2022/06/29 20:18:19
     * @description: 个人-我的订单，list
     * @param: showType
     * @param: page
     * @param: limit
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO>
     */
    @Override
    public CommonData<WxOrderListChildVO> queryAllOrder(Integer showType, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        //拿到userId
        Integer userId = getUserId();

        //根据userId和订单状态找出该用户该状态的订单列表
        List<WxOrderListChildVO> wxOrderListChildVOList = new ArrayList<>();
        if (showType == 0) {
            wxOrderListChildVOList = wxOrderMapper.selectAllorderListByUserId(userId);
        } else {
            // Map instance = OrderStatusConvert.getInstance();
            // OrderStatusConvert o = (OrderStatusConvert) instance.get(showType);
            // Integer orderStatus = o.getOrderStatus();
            Integer orderStatus = OrderStatusConvert.getStatus(showType);
            wxOrderListChildVOList = wxOrderMapper.selectOrderListByStatusByUserId(orderStatus, userId);
        }

        for (WxOrderListChildVO wxOrderListChildVO : wxOrderListChildVOList) {
            wxOrderListChildVO.setIsGroupin(false);
            Integer orderId = wxOrderListChildVO.getId();
            Integer orderStatus = wxOrderMapper.selectOrderStatusById(orderId);
            //根据订单状态，查状态码信息
            // Map instance1 = OrderStatusContentConvert.getInstance();
            // OrderStatusContentConvert o1 = (OrderStatusContentConvert) instance1.get(orderStatus);
            // String orderStatusContent = o1.getOrderStatusContent();
            String orderStatusContent = OrderStatusContentConvert.getStatusContent(orderStatus);
            wxOrderListChildVO.setOrderStatusText(orderStatusContent);
            //根据订单状态，查可操作信息
            // Map instance2 = OrderStatusHandleConvert.getInstance();
            // OrderStatusHandleConvert o2 = (OrderStatusHandleConvert) instance2.get(orderStatus);
            // WxOrderListHandleOption handler = o2.getHandler();
            WxOrderListHandleOption handler = OrderStatusHandleConvert.getOption(orderStatus);
            wxOrderListChildVO.setHandleOption(handler);

            //根据orderId查找该订单里的商品信息
            List<AdminOrderDetailGoodsVO> goodsList =
                    wxOrderMapper.selectAllOrderGoodsByOrderId(orderId);
            wxOrderListChildVO.setGoodsList(goodsList);
        }

        PageInfo<WxOrderListChildVO> wxOrderListChildVOPageInfo = new PageInfo<>(wxOrderListChildVOList);
        return CommonData.data(wxOrderListChildVOPageInfo);

    }


    /**
     * @author: ZY
     * @createTime: 2022/06/29 20:24:11
     * @description: 用户申请退款
     * @param: orderId
     * @return: void
     */
    @Override
    public void refundOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusRefund(orderId);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 23:49:00
     * @description: 我的订单-订单详情-订单发货后可确认收货，更改订单状态，确认收货时间，更新时间
     * @description: 用户确认收货
     * @param: orderId
     * @return: void
     */
    @Override
    public void confirmOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusConfirm(orderId);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 20:25:34
     * @description: 我的订单-订单详情-退款后可逻辑删除订单，更改订单deleted，更新时间
     * @param: orderId
     * @return: void
     */
    @Override
    public void deleteOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusDeleted(orderId);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/29 20:33:16
     * @description: 确认收货后评价商品，信息回显
     * @param: orderId
     * @param: goodsId
     * @return: com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO
     */
    @Override
    public AdminOrderDetailGoodsVO queryOrdersGoods(Integer orderId, Integer goodsId) {
        AdminOrderDetailGoodsVO adminOrderDetailGoodsVO = wxOrderMapper.selectOrdersGoods(orderId, goodsId);
        return adminOrderDetailGoodsVO;
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 21:27:57
     * @description: 我的订单-订单详情-确认收货后评价商品，更改更新时间，待评价商品数量
     * @param: wxOrderListCommentBO
     * @return: void
     */
    @Async
    @Override
    public void addOrderComment(WxOrderListCommentBO wxOrderListCommentBO) {
        //拿到userId
        Integer userId = getUserId();

        //拿到订单商品表里的主键id
        Integer orderGoodsId = wxOrderListCommentBO.getOrderGoodsId();
        StringBuffer sb = new StringBuffer();
        String[] picUrlsArr = wxOrderListCommentBO.getPicUrls();
        sb.append("[");
        for (String picUrl : picUrlsArr) {
            sb.append("\"").append(picUrl).append("\"").append(",");
        }
        String res = sb.toString();
        String picUrls = res.substring(0,res.length()-1)+ "]";;
       //获得实际的商品id
        Integer goodsId=wxOrderMapper.selectRealGoodsId(orderGoodsId);

        //插入商品评论表，并获得主键id，更改订单商品表的comment
        wxOrderMapper.insertOrderComment(wxOrderListCommentBO, userId, picUrls,goodsId);
        Integer commentId = wxOrderListCommentBO.getId();

        //更改订单商品表里，的商品评论状态
        wxOrderMapper.updateMarketOrderCommentStatus(commentId, orderGoodsId);

        //更改订单待评价商品数量和更新时间
        Integer orderId = wxOrderMapper.selectOrderIdByOrderGoodsId(orderGoodsId);
        wxOrderMapper.updateOrder(orderId);

    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 22:08:57
     * @description: 个人-我的订单-取消订单,更改订单状态，关闭时间，更新时间
     * @param: orderId
     * @return: void
     */
    @Override
    public void cancelOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusCancel(orderId);
    }

    /**
     * @description: 根据指定订单id获取订单和商品信息
     * @parameter: [orderId]
     * @return: com.cskaoyan.bean.vo.wxOrder.WxOrderDetailVo
     * @author: 帅关
     * @createTime: 2022/6/30 7:32
     */
    @Override
    public WxOrderDetailVo selectOrderDetailByOrderId(Integer orderId) {
        // 查询订单
        WxOrderDetailChildVo child = wxOrderMapper.selectOrderInfoByOrderId(orderId);
        // Map instance = OrderStatusHandleConvert.getInstance();
        // OrderStatusHandleConvert convert = (OrderStatusHandleConvert) instance.get(child.getOrderStatus());
        // WxOrderListHandleOption handler = convert.getHandler();
        WxOrderListHandleOption handler = OrderStatusHandleConvert.getOption(child.getOrderStatus());


        // 根据状态码获取handler和statusText
        // OrderStatusContentConvert statusConvert = (OrderStatusContentConvert) OrderStatusContentConvert.getInstance().get(child.getOrderStatus());
        // String statusText = statusConvert.getOrderStatusContent();
        String statusText = OrderStatusContentConvert.getStatusContent(child.getOrderStatus());
        child.setHandleOption(handler);
        child.setOrderStatusText(statusText);

        // 查询商品
        List<AdminOrderDetailGoodsVO> list = wxOrderMapper.selectAllInfoOrderGoodsByOrderId(orderId);
        WxOrderDetailVo detailVo = new WxOrderDetailVo();
        detailVo.setOrderGoods(list);
        detailVo.setOrderInfo(child);
        detailVo.setExpressInfo(new ArrayList<>());
        return detailVo;
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/30 11:13:38
     * @description: 预支付，更新订单状态，更新时间，付款编号，付款时间
     * @param: orderId
     * @return: void
     */
    @Override
    public void prepayOrder(Integer orderId) {
        //生成payId
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String format = simpleDateFormat.format(date);
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        String num = String.valueOf(random);

        String payId = format.concat(num);

        wxOrderMapper.updateOrderStatuPrepay(orderId, payId);
    }

    /**
     * @description: 增加订单
     * @parameter: [wxOrderSubmitBO]
     * @return: java.lang.Integer
     * @author: 帅关
     * @createTime: 2022/6/30 18:54
     */
    @Override
    public Integer addOrder(WxOrderSubmitBO wxOrderSubmitBO) {

        // 生成订单
        MarketOrder marketOrder = new MarketOrder();
        // 生成订单编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String prefix = sdf.format(new Date());
        String postfix = String.valueOf(System.currentTimeMillis());
        String orderSn = prefix + postfix.substring(postfix.length() - 6);
        marketOrder.setOrderSn(orderSn);
        // 生成订单订单状态
        marketOrder.setOrderStatus((short) 101);
        // 获取留言信息
        marketOrder.setMessage(wxOrderSubmitBO.getMessage());
        // 获取收货人信息
        getReceiverInfo(marketOrder);
        // 获取商品总费用
        getCartInfo(marketOrder, wxOrderSubmitBO);
        // 获取创建时间
        Date date = new Date();
        marketOrder.setAddTime(date);
        marketOrder.setUpdateTime(date);
        orderMapper.insert(marketOrder);
        Integer orderId = marketOrder.getId();
        updateSubmitInfo(orderId,wxOrderSubmitBO);
        return orderId;
    }


    /**
     * @description: 提交订单信息杂七杂八的表修改
     * @parameter: [orderId, userCouponId]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/30 12:06
     */
    @Async
    public void updateSubmitInfo(Integer orderId,WxOrderSubmitBO wxOrderSubmitBO){
        Integer userId = getUserId();
        Integer cartId = wxOrderSubmitBO.getCartId();
        MarketCartExample example = new MarketCartExample();
        // 判断商品是从立即购买->购买的,还是从购物车购买的
        if(cartId > 0){
            MarketCart marketCart = cartMapper.selectByPrimaryKey(cartId);
            MarketOrderGoods goods = getMarketOrderGoods(orderId, marketCart);
            orderGoodsMapper.insertSelective(goods);

            // 更新购物车信息
            cartMapper.updateCartGoodsDeletedByCartId(cartId);

            // 更新商品数量
            Short number = marketCart.getNumber();
            Integer productId = marketCart.getProductId();
            goodsProductMapper.updateGoodsNumberByPrimaryKey(productId,number);
        }else{
            // 查出该用户所有被选中的商品列表
            example.createCriteria().andUserIdEqualTo(userId)
                    .andCheckedEqualTo(true)
                    .andDeletedEqualTo(false);
            List<MarketCart> marketCarts = cartMapper.selectByExample(example);
            // 将选中的商品信息添加到订单商品表中
            for (MarketCart marketCart : marketCarts) {
                MarketOrderGoods goods = getMarketOrderGoods(orderId, marketCart);
                orderGoodsMapper.insertSelective(goods);
                // 修改商品数量
                Short number = marketCart.getNumber();
                Integer productId = marketCart.getProductId();
                goodsProductMapper.updateGoodsNumberByPrimaryKey(productId,number);
            }
            // 更新购物车信息
            cartMapper.deleteCartInfoByUserId(userId);
        }

        Integer userCouponId = wxOrderSubmitBO.getUserCouponId();
        // 更新优惠券信息
        if(userCouponId > 0){
            couponUserMapper.updateUserCouponNumberByCouponId(userCouponId);
        }



    }


    /**
     * @description: submit中获取MarketOrderGoods对象
     * @parameter: [orderId, marketCart]
     * @return: com.cskaoyan.bean.MarketOrderGoods
     * @author: 帅关
     * @createTime: 2022/6/30 17:50
     */
    private MarketOrderGoods getMarketOrderGoods(Integer orderId, MarketCart marketCart) {
        MarketOrderGoods goods = new MarketOrderGoods();
        BeanUtils.copyProperties(marketCart, goods);
        goods.setOrderId(orderId);
        goods.setId(null);
        StringBuffer sb = new StringBuffer();
        String[] specifications = marketCart.getSpecifications();
        sb.append("[");
        for (String specification : specifications) {
            sb.append("\"").append(specification).append("\"").append(",");
        }
        String res = sb.toString();
        String s = res.substring(0,res.length()-1)+ "]";
        goods.setSpecifications(s);
        return goods;
    }

    /**
     * @description: 获取购物车费用
     * @parameter: [marketOrder]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/30 10:20
     */
    private void getCartInfo(MarketOrder marketOrder, WxOrderSubmitBO wxOrderSubmitBO) {
        WxCartCheckBo cart = new WxCartCheckBo();
        BeanUtils.copyProperties(wxOrderSubmitBO, cart);
        WxCartCheckedVo checked = cartService.queryCartCheckInfo(cart);
        // 封装对象
        marketOrder.setGoodsPrice(BigDecimal.valueOf(checked.getGoodsTotalPrice()));
        marketOrder.setFreightPrice(BigDecimal.valueOf(checked.getFreightPrice()));
        marketOrder.setCouponPrice(BigDecimal.valueOf(checked.getCouponPrice()));
        marketOrder.setOrderPrice(BigDecimal.valueOf(checked.getOrderTotalPrice()));
        marketOrder.setActualPrice(BigDecimal.valueOf(checked.getActualPrice()));
        marketOrder.setComments((short) checked.getCheckedGoodsList().size());
        marketOrder.setIntegralPrice(BigDecimal.valueOf(0));
        marketOrder.setGrouponPrice(BigDecimal.valueOf(0));
    }

    /**
     * @description: 提供订单获取收货人信息
     * @parameter: [marketOrder]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/30 10:04
     */
    private void getReceiverInfo(MarketOrder marketOrder) {
        Integer userId = getUserId();
        MarketAddress addressInfo = addressMapper.selectPartAddressInfoByUserId(userId);
        marketOrder.setConsignee(addressInfo.getName())
                .setMobile(addressInfo.getTel())
                .setAddress(addressInfo.getAddressDetail())
                .setUserId(userId);
    }

    /**
     * @description: 获取用户Id
     * @parameter: []
     * @return: java.lang.Integer
     * @author: 帅关
     * @createTime: 2022/6/30 18:53
     */
    private Integer getUserId() {
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        return user.getId();
    }
}
