package com.cskaoyan.bean.vo.wxGoodsDetailVo;

import com.cskaoyan.bean.MarketGoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 23:54
 */
@Data
public class WxGoodsDetailSpecificationVo {

    private String name;

    private List<MarketGoodsSpecification> valueList;

}
