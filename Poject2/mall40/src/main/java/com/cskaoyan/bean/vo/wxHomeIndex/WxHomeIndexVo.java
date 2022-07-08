package com.cskaoyan.bean.vo.wxHomeIndex;

import com.cskaoyan.bean.*;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月28日 21:52
 */
@Data
public class WxHomeIndexVo {

    private List<MarketAd> banner;

    private List<MarketBrand> brandList;

    private List<MarketCategory> channel;

    private List<MarketCoupon> couponList;

    private List<FloorGoodsVo> floorGoodsList;

    private List<MarketGoods> hotGoodsList;

    private List<MarketGoods> newGoodsList;

    private List<MarketTopic> topicList;

}
