package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketOrderGoods;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;

import java.util.List;

/**
 *
 * @since 2022/06/29 21:25
 * @author Gzy
 */


public class WxOrderDetailVo {


    /**
     * expressInfo : []
     * orderInfo : {"consignee":"yzw","address":"北京市市辖区东城区 101","addTime":"2022-06-29 21:14:49","orderSn":"20220629723864","actualPrice":1699,"mobile":"13631974341","message":"","orderStatusText":"已付款","aftersaleStatus":0,"goodsPrice":1699,"couponPrice":0,"id":269,"freightPrice":0,"handleOption":{"cancel":false,"confirm":false,"rebuy":false,"pay":false,"comment":false,"delete":false,"aftersale":false,"refund":true}}
     * orderGoods : [{"productId":120,"addTime":"2022-06-29 21:14:49","orderId":269,"goodsId":1097004,"goodsSn":"1097004","updateTime":"2022-06-29 21:14:49","specifications":["标准"],"number":1,"picUrl":"http://yanxuan.nosdn.127.net/54f822e9c542d20566c7f70f90d52ae6.png","deleted":false,"price":1699,"comment":0,"id":316,"goodsName":"原素系列实木餐桌"}]
     */
    private List<?> expressInfo;
    private WxOrderDetailChildVo orderInfo;
    private List<AdminOrderDetailGoodsVO> orderGoods;

    public void setExpressInfo(List<?> expressInfo) {
        this.expressInfo = expressInfo;
    }

    public List<?> getExpressInfo() {
        return expressInfo;
    }

    public WxOrderDetailChildVo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(WxOrderDetailChildVo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<AdminOrderDetailGoodsVO> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<AdminOrderDetailGoodsVO> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
