package com.cskaoyan.bean.bo.adminGoodsDeleteBo;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 22:09
 */
public class AdminGoodsDeleteBo {


    /**
     * brief : 商品简介
     * keywords : 关键字1,关键字2
     * addTime : 2022-06-27 16:56:06
     * goodsSn : 1234
     * updateTime : 2022-06-27 16:56:06
     * isNew : false
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/bf2qx3zzdv4s86mm0le7.jpg
     * unit : 分
     * deleted : false
     * brandId : 1001037
     * sortOrder : 100
     * name : 商品名称
     * counterPrice : 34.56
     * id : 1181033
     * detail : <p>输入框输入的商品详细介绍</p>
     * isOnSale : false
     * retailPrice : 12.34
     * categoryId : 1010001
     * gallery : ["http://182.92.235.201:8083/wx/storage/fetch/b2vmtkyn8zju0xd5n54o.jpg","http://182.92.235.201:8083/wx/storage/fetch/l885bqoyabjpaa7eq7vw.jpg"]
     * isHot : true
     */
    private String brief;
    private String keywords;
    private String addTime;
    private String goodsSn;
    private String updateTime;
    private boolean isNew;
    private String picUrl;
    private String unit;
    private boolean deleted;
    private int brandId;
    private int sortOrder;
    private String name;
    private double counterPrice;
    private int id;
    private String detail;
    private boolean isOnSale;
    private double retailPrice;
    private int categoryId;
    private String[] gallery;
    private boolean isHot;

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounterPrice(double counterPrice) {
        this.counterPrice = counterPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setIsOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public String getBrief() {
        return brief;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUnit() {
        return unit;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public String getName() {
        return name;
    }

    public double getCounterPrice() {
        return counterPrice;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public boolean isIsOnSale() {
        return isOnSale;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }



    public boolean isIsHot() {
        return isHot;
    }
}
