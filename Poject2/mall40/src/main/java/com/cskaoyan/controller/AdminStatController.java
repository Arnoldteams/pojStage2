package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.statForm.AdminStatGoodsVO;
import com.cskaoyan.bean.vo.statForm.AdminStatOrderVO;
import com.cskaoyan.bean.vo.statForm.AdminStatUserVO;
import com.cskaoyan.service.AdminStatService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 22:47:04
 * @version:
 * @Description: admin/stat
 */


@RestController
@RequestMapping("admin/stat")
public class AdminStatController {

    @Autowired
    AdminStatService statService;

    @RequestMapping("user")
    @RequiresPermissions(value = {"admin:stat:user","*"},logical = Logical.OR)
    public BaseRespVo user() {
        AdminStatUserVO statUserVO = statService.statUser();
        return BaseRespVo.ok(statUserVO);
    }

    @RequestMapping("order")
    @RequiresPermissions(value = {"admin:stat:order","*"},logical = Logical.OR)
    public BaseRespVo order() {
        AdminStatOrderVO statOrderVO = statService.statOrder();
        return BaseRespVo.ok(statOrderVO);
    }

    @RequestMapping("goods")
    @RequiresPermissions(value = {"admin:stat:goods","*"},logical = Logical.OR)
    public BaseRespVo goods() {
        AdminStatGoodsVO statGoodsVO = statService.statGoods();
        return  BaseRespVo.ok(statGoodsVO);
    }

}
