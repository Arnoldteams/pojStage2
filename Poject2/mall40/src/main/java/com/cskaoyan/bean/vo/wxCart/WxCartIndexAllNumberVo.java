package com.cskaoyan.bean.vo.wxCart;

/**
 *
 * @since 2022/06/28 22:05
 * @author Gzy
 */


public class WxCartIndexAllNumberVo {

    /**
     * goodsCount : 86
     * checkedGoodsCount : 86
     * goodsAmount : 362657.0
     * checkedGoodsAmount : 362657.0
     */
    private int goodsCount;
    private int checkedGoodsCount;
    private double goodsAmount;
    private double checkedGoodsAmount;

    public WxCartIndexAllNumberVo setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
        return this;
    }

    public WxCartIndexAllNumberVo setCheckedGoodsCount(int checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
        return this;
    }

    public WxCartIndexAllNumberVo setGoodsAmount(double goodsAmount) {
        this.goodsAmount = goodsAmount;
        return this;
    }

    public WxCartIndexAllNumberVo setCheckedGoodsAmount(double checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
        return this;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public int getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public double getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }
}
