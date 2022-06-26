package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.DashBoardVO;
import com.cskaoyan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 14:25:04
 * @Description: 商场首页
 */


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService marketAdminService;

    @RequestMapping("dashboard")
    public BaseRespVo dashboard(){
        DashBoardVO data=marketAdminService.queryAllCount();
        return BaseRespVo.ok(data);
    }

}
