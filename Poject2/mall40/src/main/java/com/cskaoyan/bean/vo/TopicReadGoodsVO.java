package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketGoods;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xyg2597@163.com
 * @since 2022/06/26 13:49
 */
@Data
public class TopicReadGoodsVO {
    private Integer id;

    private String name;

    private String brief;

    private String picUrl;

    private Boolean isNew;

    private Boolean isHot;

    private BigDecimal counterPrice;

    private BigDecimal retailPrice;


    public void setAllFiled(MarketGoods marketGoods){
        this.setId(marketGoods.getId());
        this.setName(marketGoods.getName());
        this.setBrief(marketGoods.getBrief());
        this.setPicUrl(marketGoods.getPicUrl());
        this.setIsNew(marketGoods.getIsNew());
        this.setIsHot(marketGoods.getIsHot());
        this.setCounterPrice(marketGoods.getCounterPrice());
        this.setRetailPrice(marketGoods.getRetailPrice());
    }
}
