package com.cskaoyan.controller;

import com.cskaoyan.bean.MarketGoodsProduct;
import com.cskaoyan.bean.bo.adminGoodsCreateBo.AdminGoodsCreateBo;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.bo.adminGoodsDeleteBo.AdminGoodsDeleteBo;
import com.cskaoyan.bean.bo.adminGoodsUpdateBo.AdminGoodsUpdateBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.validParam.ValidParamAdminGoodsCreate;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.AdminGoodsCatAndBrandVo;
import com.cskaoyan.bean.vo.adminGoodsDetailVo.AdminGoodsDetailVo;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequiresPermissions(value = {"admin:goods:list", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsList(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId) {
        CommonData<MarketGoods> data = adminGoodsService.qurryAllGoods(baseParam, goodsSn, name, goodsId);
        return BaseRespVo.ok(data);
    }

    /**
     * @author: Sssd
     * @description: 显示商品种类和产品商列表
     */
    @RequestMapping("catAndBrand")
    @RequiresPermissions(value = {"admin:goods:catAndBrand", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsCatAndBrand() {
        AdminGoodsCatAndBrandVo vo = adminGoodsService.qurryAllCatAndBrand();
        return BaseRespVo.ok(vo);
    }

    /**
     * @author: 重新振作的Sssd
     * @description: 商品上架
     */
    @LogAnnotation(value = "增加商品", comment = "帅气的 Sssd 增加商品")
    @PostMapping("create")
    @RequiresPermissions(value = {"admin:goods:create", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsCreate(@RequestBody AdminGoodsCreateBo bo) {
        int i = adminGoodsService.addGoods(bo);
        if (i == 0) {
            return BaseRespVo.invalidUsername("商品编号重复");
        }
        return BaseRespVo.ok(null);
    }

    /**
     * @param id 商品 id
     * @author: 很兴奋的 Sssd
     * @description: 显示商品详情
     */
    @RequestMapping("detail")
    @RequiresPermissions(value = {"admin:goods:detail", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsDetail(Integer id) {
        AdminGoodsDetailVo vo = adminGoodsService.qurryGoodById(id);
        return BaseRespVo.ok(vo);
    }

    /**
     * @param bo 请求参数
     * @author: 很兴奋的 Sssd
     * @description: 修改商品信息
     */
    @LogAnnotation(value = "修改商品信息", comment = "帅气的 Sssd 修改商品信息")
    @PostMapping("update")
    @RequiresPermissions(value = {"admin:goods:update", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsUpdate(@RequestBody AdminGoodsUpdateBo bo) {
        int i = adminGoodsService.modifyGoods(bo);
        if (i == 0) {
            return BaseRespVo.invalidUsername("商品编号重复");
        }

        return BaseRespVo.ok(null);
    }

    /**
     * @author: 炒鸡兴奋的 Sssd
     * @description: 删除商品
     */
    @LogAnnotation(value = "删除商品", comment = "帅气的 Sssd 删除商品")
    @PostMapping("delete")
    @RequiresPermissions(value = {"admin:goods:delete", "*"}, logical = Logical.OR)
    public BaseRespVo adminGoodsDelete(@RequestBody AdminGoodsDeleteBo bo) {
        adminGoodsService.deleteGoods(bo);
        return BaseRespVo.ok(null);
    }

}
