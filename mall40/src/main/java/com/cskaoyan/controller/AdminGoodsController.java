package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品模块
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月26日 13:21
 */
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    // 注入 adminGoodsService
    @Autowired
    AdminGoodsService adminGoodsService;


    /**
     * @author: 无敌帅的 Sssd
     * @param baseParam 分页数据
     * @param goodsSn 不知道什么玩意儿，和 goodsid 一样
     * @param name 商品名称
     * @param goodsId 商品 id
     * @return BaseRespVo
     */
    @RequestMapping("list")
    public BaseRespVo adminGoodsList(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId) {
        CommonData<MarketGoods> data = adminGoodsService.qurryAllGoods(baseParam, goodsSn, name, goodsId);
        return BaseRespVo.ok(data);
    }




}
