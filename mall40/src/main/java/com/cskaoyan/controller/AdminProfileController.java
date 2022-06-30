package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZY
 * @createTime: 2022年06月30日 22:00:23
 * @Description: 网页不报错
 */

@RestController
@RequestMapping("admin/profile")
public class AdminProfileController {

    @RequestMapping("nnotice")
    @RequiresPermissions(value = {"admin:profile:nnotice", "*"}, logical = Logical.OR)
    public BaseRespVo nnotice() {
        return BaseRespVo.ok();
    }

}
