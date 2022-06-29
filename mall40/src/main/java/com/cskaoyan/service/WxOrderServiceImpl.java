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
//        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
//        MarketUser user = (MarketUser) principals.getPrimaryPrincipal();
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = user.getId();

        //根据userId和订单状态找出该用户该状态的订单列表
        List<MarketOrder> orderList = new ArrayList<>();
        if (showType == 0) {
            orderList = wxOrderMapper.selectAllorderListByUserId(userId);
        } else {
            Map instance = OrderStatusConvert.getInstance();
            OrderStatusConvert o = (OrderStatusConvert) instance.get(showType);
            Integer orderStatus = o.getOrderStatus();
            orderList = wxOrderMapper.selectOrderListByStatusByUserId(orderStatus, userId);
        }

        //遍历找出来的orderList，给VO里的wxOrderListChildVOList赋值
        List<WxOrderListChildVO> wxOrderListChildVOList = new ArrayList<>();
        for (MarketOrder order : orderList) {
            WxOrderListChildVO wxOrderListChildVO = new WxOrderListChildVO();
            //给WxOrderListChildVO的各个成员变量赋值
            wxOrderListChildVO.setActualPrice(order.getActualPrice());
            wxOrderListChildVO.setAftersaleStatus(order.getAftersaleStatus());
            wxOrderListChildVO.setId(order.getId());
            wxOrderListChildVO.setIsGroupin(false);
            wxOrderListChildVO.setOrderSn(order.getOrderSn());

            //根据订单状态，查状态码信息
            Integer orderStatus = Integer.valueOf(order.getOrderStatus());
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
            Integer orderId = order.getId();
            List<AdminOrderDetailGoodsVO> goodsList =
                    wxOrderMapper.selectAllOrderGoodsByOrderId(orderId);
            wxOrderListChildVO.setGoodsList(goodsList);

            //将封装好的VO加进List
            wxOrderListChildVOList.add(wxOrderListChildVO);
        }

        // PageInfo<WxOrderListChildVO> wxOrderListChildVOPageInfo = new PageInfo<>(wxOrderListChildVOList);
        PageInfo<MarketOrder> marketOrderPageInfo = new PageInfo<>(orderList);
        return CommonData.data(marketOrderPageInfo);
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
        MarketUser user = (MarketUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = user.getId();

        String picUrls = Arrays.toString(wxOrderListCommentBO.getPicUrls());
        wxOrderMapper.insertOrderComment(wxOrderListCommentBO,userId,picUrls);
    }

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

}
