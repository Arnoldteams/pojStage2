package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone
 * @date 2022/06/25 11:05
 */
@RestController
//@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    UserService userService;

    /*@RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit, String sort,String order) {

    }*/

    @RequestMapping("list")
    //public BaseRespVo list(String username, Integer page, Integer limit, String sort, String order) {
    public BaseRespVo list(String username, BasePageInfo info) {
        CommonData<User> data =  userService.query(username,info);
        return BaseRespVo.ok(data);
    }
}
