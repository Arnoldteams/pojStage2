package com.cskaoyan.controller;

import com.cskaoyan.bean.bo.adminGoodsCreateBo.AdminGoodsCreateBo;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.bo.adminGoodsDeleteBo.AdminGoodsDeleteBo;
import com.cskaoyan.bean.bo.adminGoodsUpdateBo.AdminGoodsUpdateBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.AdminGoodsCatAndBrandVo;
import com.cskaoyan.bean.vo.adminGoodsDetailVo.AdminGoodsDetailVo;
import com.cskaoyan.service.AdminGoodsService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * @param baseParam 分页数据
     * @param goodsSn   不知道什么玩意儿，和 goodsid 一样
     * @param name      商品名称
     * @param goodsId   商品 id
     * @return BaseRespVo
     * @author: 无敌帅的 Sssd
     */
    @RequestMapping("list")
    public BaseRespVo adminGoodsList(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId) {
        CommonData<MarketGoods> data = adminGoodsService.qurryAllGoods(baseParam, goodsSn, name, goodsId);
        return BaseRespVo.ok(data);
    }

    /**
     * @author: Sssd
     * @description: 显示商品种类和产品商列表
     */
    @RequestMapping("catAndBrand")
    public BaseRespVo adminGoodsCatAndBrand() {
        AdminGoodsCatAndBrandVo vo = adminGoodsService.qurryAllCatAndBrand();
        return BaseRespVo.ok(vo);
    }


    /**
     * @author: 重新振作的Sssd
     * @description: 商品上架
     */
    @PostMapping("create")
    public BaseRespVo adminGoodsCreate(@RequestBody AdminGoodsCreateBo bo) {
        adminGoodsService.addGoods(bo);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: 很兴奋的 Sssd
     * @param id 商品 id
     * @description: 显示商品详情
     */
    @RequestMapping("detail")
    public BaseRespVo adminGoodsDetail(Integer id) {
        AdminGoodsDetailVo vo = adminGoodsService.qurryGoodById(id);
        return BaseRespVo.ok(vo);
    }

    /**
     * @author: 很兴奋的 Sssd
     * @param bo 请求参数
     * @description: 修改商品信息
     */
    @PostMapping("update")
    public BaseRespVo adminGoodsUpdate(@RequestBody AdminGoodsUpdateBo bo) {
        adminGoodsService.modifyGoods(bo);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: 炒鸡兴奋的 Sssd
     * @description: 删除商品
     */
    @PostMapping("delete")
    public BaseRespVo adminGoodsDelete(@RequestBody AdminGoodsDeleteBo bo) {
        adminGoodsService.deleteGoods(bo);
        return BaseRespVo.ok(null);
    }

}
