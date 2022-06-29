package com.cskaoyan.service;

import com.cskaoyan.bean.bo.wxOrder.WxOrderListCommentBO;
import com.cskaoyan.bean.bo.wxOrder.WxOrderSubmitBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.bean.vo.wxOrder.WxOrderSubmitVO;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;

public interface WxOrderService {

    CommonData<WxOrderListChildVO> queryAllOrder(Integer showType, Integer page, Integer limit);

    void refundOrder(Integer orderId);

    void confirmOrder(Integer orderId);

    void deleteOrder(Integer orderId);

    AdminOrderDetailGoodsVO queryOrdersGoods(Integer orderId, Integer goodsId);

    void addOrderComment(WxOrderListCommentBO wxOrderListCommentBO);


    void cancelOrder(Integer orderId);

    WxOrderDetailVo selectOrderDetailByOrderId(Integer orderId);

}
