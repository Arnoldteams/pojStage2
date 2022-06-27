package com.cskaoyan.bean.bo.adminGoodsCreateBo;


import com.cskaoyan.bean.MarketGoodsAttribute;
import com.cskaoyan.bean.MarketGoodsProduct;
import com.cskaoyan.bean.MarketGoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @description: 商品模块 - 增加商品功能 - 参数接收
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 8:09
 */
@Data

public class AdminGoodsCreateBo {

    private AdminGoodsCreateGoodsBo goods;

    private List<AdminGoodsCreateSpecificationBo> specifications;

    private List<AdminGoodsCreateProductBo> products;

    private List<AdminGoodsCreateAttributeBo> attributes;

}
