package com.cskaoyan.mapper;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.*;
import com.cskaoyan.bean.bo.wxOrder.WxOrderListCommentBO;
import com.cskaoyan.bean.bo.wxOrder.WxOrderListHandleOption;
import com.cskaoyan.bean.bo.wxOrder.WxOrderSubmitBO;
import com.cskaoyan.bean.vo.statForm.OrderRowsEntity;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.bean.vo.wxOrder.WxOrderDetailChildVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxOrderMapper {
    long countByExample(MarketOrderExample example);

    int deleteByExample(MarketOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrder record);

    int insertSelective(MarketOrder record);

    List<MarketOrder> selectByExample(MarketOrderExample example);

    MarketOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByExample(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByPrimaryKeySelective(MarketOrder record);

    int updateByPrimaryKey(MarketOrder record);


    void updateOrderStatus(AdminOrderShipBO adminOrderShipBO);


    MarketOrder selectOrder(Integer id);

    MarketUser selectUser(Integer id);

    List<OrderRowsEntity> countOrderByAddTime();


    void updateOrderRefund(AdminOrderRefundBO adminOrderRefundBO);

    String selectAdminComment(Integer commentId);

    void updateOrderComment(AdminOrderReplyBO adminOrderReplyBO);


//    wx新增的方法

    MarketAddress selectAddressInfoById(Integer addressId);

    MarketCart selectCartInfoById(Integer cartId);

    MarketCoupon selectCouponPrice(Integer couponIdId);

    MarketOrder addOrder(@Param("address") MarketAddress address, @Param("coupon") MarketCoupon coupon,
                         @Param("wxOrdersubmitBO") WxOrderSubmitBO wxOrdersubmitBO);

    Integer selectFreightMin();

    Integer selectFreightValue();


    void updateUserOrderStatusRefund(Integer orderId);

    void updateUserOrderStatusConfirm(Integer orderId);

    void updateUserOrderStatusDeleted(Integer orderId);

    void updateUserOrderStatusPay(Integer orderId);

    AdminOrderDetailGoodsVO selectOrdersGoods(@Param("orderId") Integer orderId, @Param("goodsId") Integer goodsId);

    void insertOrderComment(@Param("wxOrderListCommentBO") WxOrderListCommentBO wxOrderListCommentBO,
                            @Param("userId") Integer userId, @Param("picUrls") String picUrls);

    List<AdminOrderDetailGoodsVO> selectAllOrderGoodsByOrderId(Integer orderId);

    WxOrderListHandleOption selectHandleOption(Integer orderId);


    void updateUserOrderStatusCancel(Integer orderId);

    List<WxOrderListChildVO> selectAllorderListByUserId(Integer userId);

    List<WxOrderListChildVO> selectOrderListByStatusByUserId(@Param("orderStatus") Integer orderStatus, @Param("userId") Integer userId);

    Integer selectOrderStatusById(Integer orderId);


    WxOrderDetailChildVo selectOrderInfoByOrderId(@Param("orderId") Integer orderId);

    List<AdminOrderDetailGoodsVO> selectAllInfoOrderGoodsByOrderId(Integer orderId);

    void updateOrderStatuPrepay(@Param("orderId") Integer orderId, @Param("payId") String payId);

    Integer selectOrderIdByOrderGoodsId(Integer orderGoodsId);

    void updateOrder(Integer orderId);

    void updateMarketOrderCommentStatus(Integer commentId);

}