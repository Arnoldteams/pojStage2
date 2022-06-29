package com.cskaoyan.bean.bo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 23:42:57
 * @Description: order-list里的
 */

@NoArgsConstructor
@AllArgsConstructor
public class WxOrderListHandleOption {
    Boolean aftersale;
    Boolean cancel;
    Boolean comment;
    Boolean confirm;
    Boolean delete;
    Boolean pay;
    Boolean rebuy;
    Boolean refund;

    public Boolean getAftersale() {
        return aftersale;
    }

    public WxOrderListHandleOption setAftersale(Boolean aftersale) {
        this.aftersale = aftersale;
        return this;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public WxOrderListHandleOption setCancel(Boolean cancel) {
        this.cancel = cancel;
        return this;
    }

    public Boolean getComment() {
        return comment;
    }

    public WxOrderListHandleOption setComment(Boolean comment) {
        this.comment = comment;
        return this;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public WxOrderListHandleOption setConfirm(Boolean confirm) {
        this.confirm = confirm;
        return this;
    }

    public Boolean getDelete() {
        return delete;
    }

    public WxOrderListHandleOption setDelete(Boolean delete) {
        this.delete = delete;
        return this;
    }

    public Boolean getPay() {
        return pay;
    }

    public WxOrderListHandleOption setPay(Boolean pay) {
        this.pay = pay;
        return this;
    }

    public Boolean getRebuy() {
        return rebuy;
    }

    public WxOrderListHandleOption setRebuy(Boolean rebuy) {
        this.rebuy = rebuy;
        return this;
    }

    public Boolean getRefund() {
        return refund;
    }

    public WxOrderListHandleOption setRefund(Boolean refund) {
        this.refund = refund;
        return this;
    }
}
