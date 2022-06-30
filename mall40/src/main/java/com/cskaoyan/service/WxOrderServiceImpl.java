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
import org.springframework.validation.ObjectError;

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
            Map instance = OrderStatusConvert.getInstance();
            OrderStatusConvert o = (OrderStatusConvert) instance.get(showType);
            Integer orderStatus = o.getOrderStatus();
            wxOrderListChildVOList = wxOrderMapper.selectOrderListByStatusByUserId(orderStatus, userId);
        }

        for (WxOrderListChildVO wxOrderListChildVO : wxOrderListChildVOList) {
            wxOrderListChildVO.setIsGroupin(false);
            Integer orderId = wxOrderListChildVO.getId();
            Integer orderStatus = wxOrderMapper.selectOrderStatusById(orderId);
            //根据订单状态，查状态码信息
            Map instance1 = OrderStatusContentConvert.getInstance();
            OrderStatusContentConvert o1 = (OrderStatusContentConvert) instance1.get(orderStatus);
            String orderStatusContent = o1.getOrderStatusContent();
            wxOrderListChildVO.setOrderStatusText(orderStatusContent);
            //根据订单状态，查可操作信息
            Map instance2 = OrderStatusHandleConvert.getInstance();
            OrderStatusHandleConvert o2 = (OrderStatusHandleConvert) instance2.get(orderStatus);
            WxOrderListHandleOption handler = o2.getHandler();
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
     * @description: 退款后可逻辑删除订单
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
     * @description: 确认收货后评价商品
     * @param: wxOrderListCommentBO
     * @return: void
     */
    @Override
    public void addOrderComment(WxOrderListCommentBO wxOrderListCommentBO) {
        //拿到userId
        Integer userId = getUserId();

        String picUrls = Arrays.toString(wxOrderListCommentBO.getPicUrls());
        wxOrderMapper.insertOrderComment(wxOrderListCommentBO, userId, picUrls);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 22:08:57
     * @description: 取消订单
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
        Map instance = OrderStatusHandleConvert.getInstance();
        OrderStatusHandleConvert convert = (OrderStatusHandleConvert) instance.get(child.getOrderStatus());
        WxOrderListHandleOption handler = convert.getHandler();

        // 根据状态码获取handler和statusText
        OrderStatusContentConvert statusConvert = (OrderStatusContentConvert) OrderStatusContentConvert.getInstance().get(child.getOrderStatus());
        String statusText = statusConvert.getOrderStatusContent();
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
        getCartInfo(marketOrder,wxOrderSubmitBO);
        // 获取创建时间
        Date date = new Date();
        marketOrder.setAddTime(date);
        marketOrder.setUpdateTime(date);
        orderMapper.insert(marketOrder);
        Integer orderId = marketOrder.getId();
        updateSubmitInfo(orderId,wxOrderSubmitBO.getUserCouponId());
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
    public void updateSubmitInfo(Integer orderId,Integer userCouponId){
        Integer userId = getUserId();
        // 查出该用户所有被选中的商品列表
        MarketCartExample example = new MarketCartExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andCheckedEqualTo(true)
                .andDeletedEqualTo(false);
        List<MarketCart> marketCarts = cartMapper.selectByExample(example);
        // 将选中的商品信息添加到订单商品表中
        for (MarketCart marketCart : marketCarts) {
            MarketOrderGoods goods = new MarketOrderGoods();
            BeanUtils.copyProperties(marketCart,goods);
            goods.setOrderId(orderId);
            goods.setSpecifications(Arrays.toString(marketCart.getSpecifications()));
            orderGoodsMapper.insertSelective(goods);
        }

        // 更新购物车信息
        cartMapper.deleteCartInfoByUserId(userId);

        // 更新优惠券信息
        if(userCouponId > 0){
            couponUserMapper.updateUserCouponNumberByCouponId(userCouponId);
        }

    }


    /**
     * @description: 获取购物车费用
     * @parameter: [marketOrder]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/30 10:20
     */
    private void getCartInfo(MarketOrder marketOrder,WxOrderSubmitBO wxOrderSubmitBO) {
        WxCartCheckBo cart = new WxCartCheckBo();
        cart.setCouponId(wxOrderSubmitBO.getCouponId());
        cart.setUserCouponId(wxOrderSubmitBO.getUserCouponId());
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

    private Integer getUserId(){
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        return user.getId();
    }

}
