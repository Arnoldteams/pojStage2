package com.cskaoyan.service;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;

/**
 * @author: 无敌帅的 Sssd
 * @description: 商品模块业务层接口
 */
public interface AdminGoodsService {

    /**
     * @author: 无敌帅的 Sssd
     * 管理员模块 - 商品管理 - 查询显示所有商品列表
     */
    CommonData<MarketGoods> qurryAllGoods(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId);
}
