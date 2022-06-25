package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

//shiro整合之后，在做具体的开发
//响应结果都是JSON，Controller组件上我们都用@RestController注解
@RestController
@RequestMapping("admin/auth")
public class AuthController {

    /**
     * Shiro
     * 如果参数比较少，类型比较简单的话，使用map来接收也可以
     */
    @PostMapping("login")
    public BaseRespVo<LoginUserData> login(@RequestBody Map map) {
        String username = (String)map.get("username");
        String password = (String)map.get("password");


        LoginUserData loginUserData = new LoginUserData();
        AdminInfoBean adminInfo = new AdminInfoBean();
        adminInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        adminInfo.setNickName("admin123");
        loginUserData.setAdminInfo(adminInfo);
         loginUserData.setToken("c4d988f3-e4c5-4e46-b6ce-13b9e008ea20");
        return BaseRespVo.ok(loginUserData);
    }



    @RequestMapping("info")
    public BaseRespVo info(String token) {


        //开发完shiro之后，再整合
        InfoData infoData = new InfoData();
        infoData.setName("admin123");

        //根据primaryPrincipal做查询
        infoData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        infoData.setRoles(roles);
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        infoData.setPerms(perms);


        return BaseRespVo.ok(infoData);
    }
}
