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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:47:16
 * @Description: 微信-订单
 */

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    WxOrderMapper wxOrderMapper;


    @Override
    public CommonData<WxOrderListChildVO> queryAllOrder(Integer showType, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        //编一个userId，回头再改
        Integer userId = 1;
        //根据userId和订单状态找出该用户该状态的订单列表
        List<MarketOrder> orderList = new ArrayList<>();
        if (showType == 0) {
            orderList = wxOrderMapper.selectAllorderListByUerId(userId);
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

            Integer orderStatus = Integer.valueOf(order.getOrderStatus());
            Map instance = OrderStatusContentConvert.getInstance();
            OrderStatusContentConvert o = (OrderStatusContentConvert) instance.get(orderStatus);
            String orderStatusContent = o.getOrderStatusContent();
            wxOrderListChildVO.setOrderStatusText(orderStatusContent);

            //根据orderId查找该订单里的商品信息
            Integer orderId = order.getId();
            List<AdminOrderDetailGoodsVO> goodsList =
                    wxOrderMapper.selectAllOrderGoodsByOrderId(orderId);
            wxOrderListChildVO.setGoodsList(goodsList);

            //根据orderId查找该订单的各个状态位
//            WxOrderListHandleOption handleOption =
//                    wxOrderMapper.selectHandleOption(orderId);
            WxOrderListHandleOption handleOption = new WxOrderListHandleOption();




            wxOrderListChildVO.setHandleOption(handleOption);


            wxOrderListChildVOList.add(wxOrderListChildVO);

        }

        PageInfo<WxOrderListChildVO> wxOrderListChildVOPageInfo = new PageInfo<>(wxOrderListChildVOList);
        return CommonData.data(wxOrderListChildVOPageInfo);
    }


    @Override
    public void refundOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusRefund(orderId);
    }

    @Override
    public void confirmOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusConfirm(orderId);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        wxOrderMapper.updateUserOrderStatusDeleted(orderId);
    }


    @Override
    public AdminOrderDetailGoodsVO queryOrdersGoods(Integer orderId, Integer goodsId) {
        AdminOrderDetailGoodsVO adminOrderDetailGoodsVO = wxOrderMapper.selectOrdersGoods(orderId, goodsId);
        return adminOrderDetailGoodsVO;
    }

    @Override
    public void addOrderComment(WxOrderListCommentBO wxOrderListCommentBO) {
        wxOrderMapper.insertOrderComment(wxOrderListCommentBO);
    }

    @Override
    public WxOrderDetailVo selectOrderDetailByOrderId(Integer orderId) {
        // 查询订单
        WxOrderDetailChildVo child = wxOrderMapper.selectOrderInfoByOrderId(orderId);
        WxOrderListHandleOption handler = (WxOrderListHandleOption) OrderStatusHandleConvert.getInstance().get(child.getOrderStatus());
        String statusText = (String) OrderStatusContentConvert.getInstance().get(orderId);
        child.setHandleOption(handler);
        child.setOrderStatusText(statusText);

        // 查询商品
        List<AdminOrderDetailGoodsVO> list = wxOrderMapper.selectAllOrderGoodsByOrderId(orderId);
        WxOrderDetailVo detailVo = new WxOrderDetailVo();
        detailVo.setOrderGoods(list);
        detailVo.setOrderInfo(child);
        detailVo.setExpressInfo(new ArrayList<>());
        return detailVo;
    }

}
