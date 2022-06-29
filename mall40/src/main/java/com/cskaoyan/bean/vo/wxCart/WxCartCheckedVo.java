package com.cskaoyan.bean.vo.wxCart;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketCart;

import java.util.List;

/**
 *
 * @since 2022/06/29 16:41
 * @author Gzy
 */


public class WxCartCheckedVo {


    /**
     * grouponRulesId : 0
     * actualPrice : 0
     * orderTotalPrice : 0
     * cartId : 0
     * userCouponId : 95
     * couponId : 67
     * goodsTotalPrice : 138.0
     * addressId : 16
     * grouponPrice : 0
     * checkedAddress : {"addTime":"2022-06-29 15:14:01","city":"市辖区","county":"东城区","updateTime":"2022-06-29 15:14:01","userId":1,"areaCode":"110101","isDefault":true,"addressDetail":"朝阳小道","deleted":false,"province":"北京市","name":"刘中磊","tel":"13312341234","id":16}
     * couponPrice : 500.0
     * availableCouponLength : 0
     * freightPrice : 0
     * checkedGoodsList : [{"productId":153,"addTime":"2022-06-29 15:42:44","goodsId":1110019,"goodsSn":"1110019","updateTime":"2022-06-29 15:57:02","userId":1,"specifications":["标准"],"number":2,"picUrl":"http://yanxuan.nosdn.127.net/1e7e392b6fc9da99dc112197b7444eec.png","deleted":false,"price":69,"checked":true,"id":182,"goodsName":"宠物合金钢安全除菌指甲护理组合"}]
     */
    private int grouponRulesId;
    private int actualPrice;
    private int orderTotalPrice;
    private int cartId;
    private int userCouponId;
    private int couponId;
    private double goodsTotalPrice;
    private int addressId;
    private int grouponPrice;
    private MarketAddress checkedAddress;
    private double couponPrice;
    private int availableCouponLength;
    private int freightPrice;
    private List<MarketCart> checkedGoodsList;

    public void setGrouponRulesId(int grouponRulesId) {
        this.grouponRulesId = grouponRulesId;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserCouponId(int userCouponId) {
        this.userCouponId = userCouponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public void setGoodsTotalPrice(double goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setGrouponPrice(int grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public void setAvailableCouponLength(int availableCouponLength) {
        this.availableCouponLength = availableCouponLength;
    }

    public void setFreightPrice(int freightPrice) {
        this.freightPrice = freightPrice;
    }

    public int getGrouponRulesId() {
        return grouponRulesId;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserCouponId() {
        return userCouponId;
    }

    public int getCouponId() {
        return couponId;
    }

    public double getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getGrouponPrice() {
        return grouponPrice;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public int getAvailableCouponLength() {
        return availableCouponLength;
    }

    public int getFreightPrice() {
        return freightPrice;
    }

    public MarketAddress getCheckedAddress() {
        return checkedAddress;
    }

    public void setCheckedAddress(MarketAddress checkedAddress) {
        this.checkedAddress = checkedAddress;
    }

    public List<MarketCart> getCheckedGoodsList() {
        return checkedGoodsList;
    }

    public void setCheckedGoodsList(List<MarketCart> checkedGoodsList) {
        this.checkedGoodsList = checkedGoodsList;
    }
}
