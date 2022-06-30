package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.wxOrder.*;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailChildVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.bean.vo.wxOrder.WxOrderSubmitVO;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import com.cskaoyan.mapper.WxOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:47:16
 * @Description: 微信-订单
 */

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper wxOrderMapper;


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
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = user.getId();

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
     * @description: 我的订单-订单详情-订单发货后可确认收货，更改订单状态，确认收货时间，更新时间
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
    @Override
    public void addOrderComment(WxOrderListCommentBO wxOrderListCommentBO) {
        //拿到userId
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = user.getId();

        //拿到订单商品表里的主键id
        Integer orderGoodsId = wxOrderListCommentBO.getOrderGoodsId();
        String picUrls = Arrays.toString(wxOrderListCommentBO.getPicUrls());

        //插入商品评论表，并获得主键id，更改订单商品表的comment
        wxOrderMapper.insertOrderComment(wxOrderListCommentBO, userId, picUrls);
        Integer commentId = wxOrderListCommentBO.getId();

        //更改订单商品表里，的商品评论状态
        wxOrderMapper.updateMarketOrderCommentStatus(commentId);

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
        Map instance = OrderStatusHandleConvert.getInstance();
        OrderStatusHandleConvert convert = (OrderStatusHandleConvert) instance.get(child.getOrderStatus());
        WxOrderListHandleOption handler = convert.getHandler();

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

}
