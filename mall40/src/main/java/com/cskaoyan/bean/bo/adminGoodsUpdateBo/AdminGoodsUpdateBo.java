package com.cskaoyan.bean.bo.adminGoodsUpdateBo;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsAttribute;
import com.cskaoyan.bean.MarketGoodsProduct;
import com.cskaoyan.bean.MarketGoodsSpecification;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 21:47
 */
@Data
public class AdminGoodsUpdateBo {

    private MarketGoods goods;

    private List<MarketGoodsSpecification> specifications;

    private List<MarketGoodsProduct> products;

    private List<MarketGoodsAttribute> attributes;

}
