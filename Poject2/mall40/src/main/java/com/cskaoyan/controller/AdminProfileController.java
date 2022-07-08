package com.cskaoyan.controller;


import com.cskaoyan.bean.BaseRespVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.bo.AdminOrderListBO;
import com.cskaoyan.bean.bo.AdminProfilePasswordBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 * @Author: ZY
 * @createTime: 2022年06月30日 22:00:23
 * @Description: 网页不报错
 */

///
// * 修改密码
// *
// * @author sprinkle
// * @since 2022/06/30 21:10
// */

@RestController
@RequestMapping("admin/profile")
public class AdminProfileController {


    @RequestMapping("nnotice")
    @RequiresPermissions(value = {"admin:profile:nnotice", "*"}, logical = Logical.OR)
    public BaseRespVo nnotice() {
        return BaseRespVo.ok();
    }


    @Autowired
    AdminProfileService adminProfileService;

    @LogAnnotation(value = "修改密码")
    @PostMapping ("password")
    public BaseRespVo list(@RequestBody@Validated AdminProfilePasswordBO admin, BindingResult bindingResult) {
        String errMsg = adminProfileService.updatePassword(admin);

        if(errMsg == null){
            return BaseRespVo.invalidAuth("账户密码不对");
        }

        return BaseRespVo.ok();
    }

}
