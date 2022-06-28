package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.config.realm.MarketToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public BaseRespVo login(@RequestBody Map map) {
//        $2a$10$.rEfyBb/GURD9P2p0fRg/OAJGloGNDkJS4lY0cQHeqDgsbdTylBpu

        String username = (String)map.get("username");
        String password = (String)map.get("password");

        //在这里要执行登录，Subject.login
        // → Subject只能作为局部变量，不能作为全局变量
        Subject subject = SecurityUtils.getSubject();
        // 执行登录（认证）
        //UsernamePasswordToken authenticationToken = new UsernamePasswordToken(username,password);
        MarketToken authenticationToken = new MarketToken(username, password, "admin");
        try {
            // realm.doGetAuthenticationInfo
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        // 判断是否认证成功
        boolean authenticated = subject.isAuthenticated();
        // 可以获得session信息，也可以获得其sessionId
        Session session = subject.getSession();

        session.setAttribute("username",username);
        // 获得Principal信息 → realm的doGetAuthorizationInfo方法构造的认证信息里的第一个参数
        MarketAdmin primaryPrincipal = (MarketAdmin) subject.getPrincipals().getPrimaryPrincipal();


        LoginUserData loginUserData = new LoginUserData();
        AdminInfoBean adminInfo = new AdminInfoBean();

        adminInfo.setAvatar(primaryPrincipal.getAvatar());
        adminInfo.setNickName(primaryPrincipal.getUsername());

        loginUserData.setAdminInfo(adminInfo);
        //loginUserData.setToken("c4d988f3-e4c5-4e46-b6ce-13b9e008ea20");
        loginUserData.setToken((String) session.getId());
        //响应sessionId信息给到前端，前端发送请求的时候，通过请求头携带了session的id信息
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
