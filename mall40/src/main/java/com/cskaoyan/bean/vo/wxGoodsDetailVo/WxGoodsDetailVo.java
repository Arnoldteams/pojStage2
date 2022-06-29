package com.cskaoyan.bean.vo.wxGoodsDetailVo;

import com.cskaoyan.bean.*;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 22:13
 */
@Data
public class WxGoodsDetailVo {

    private List<MarketGoodsAttribute> attribute;

    private MarketBrand brand;

    private WxGoodsDetailCommentVo comment;

    private List<MarketGrouponRules> groupon;

    private MarketGoods info;

    private List<MarketIssue> issue;

    private List<MarketGoodsProduct> productList;

    private Boolean share;

    private String shareImage;

    private List<MarketGoodsSpecification> specificationList;

    private Integer userHasCollect;

}
