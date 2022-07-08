package com.cskaoyan.bean.vo.wxHomeIndex;

import com.cskaoyan.bean.MarketGoods;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月28日 22:25
 */
@Data
public class FloorGoodsVo {

    private List<MarketGoods> goodsList;

    private Integer id;

    private String name;

}
