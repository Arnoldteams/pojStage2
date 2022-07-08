package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.bo.wxOrder.WxOrderListHandleOption;

/**
 *
 * @since 2022/06/29 21:37
 * @author Gzy
 */


public class WxOrderDetailChildVo {


    /**
     * consignee : yzw
     * address : 北京市市辖区东城区 101
     * addTime : 2022-06-29 21:14:49
     * orderSn : 20220629723864
     * actualPrice : 1699.0
     * mobile : 13631974341
     * message :
     * orderStatusText : 已付款
     * aftersaleStatus : 0
     * goodsPrice : 1699.0
     * couponPrice : 0.0
     * id : 269
     * freightPrice : 0.0
     * handleOption : {"cancel":false,"confirm":false,"rebuy":false,"pay":false,"comment":false,"delete":false,"aftersale":false,"refund":true}
     */
    private String consignee;
    private String address;
    private String addTime;
    private String orderSn;
    private double actualPrice;
    private String mobile;
    private String message;
    private String orderStatusText;
    private int aftersaleStatus;
    private double goodsPrice;
    private double couponPrice;
    private int id;
    private double freightPrice;
    private Integer orderStatus;
    private WxOrderListHandleOption handleOption;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public void setAftersaleStatus(int aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFreightPrice(double freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getAddress() {
        return address;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMessage() {
        return message;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public int getAftersaleStatus() {
        return aftersaleStatus;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public int getId() {
        return id;
    }

    public double getFreightPrice() {
        return freightPrice;
    }

    public WxOrderListHandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(WxOrderListHandleOption handleOption) {
        this.handleOption = handleOption;
    }
}
