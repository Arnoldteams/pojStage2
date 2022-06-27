package com.cskaoyan.service;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.bo.adminGoodsCreateBo.AdminGoodsCreateBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.AdminGoodsCatAndBrandVo;

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

    /**
     * @author: 很疲惫的 Sssd
     * @description: 商品模块，显示商品种类和商品生产商列表
     */
    AdminGoodsCatAndBrandVo qurryAllCatAndBrand();

    /**
     * @author: Sssd
     * @description: 商品模块 - 商品添加
     * @param bo 传入的 bo 类参数
     */
    void addGoods(AdminGoodsCreateBo bo);
}
