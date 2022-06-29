package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.configuration.realm.MarketToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xyg2597@163.com
 * @since 2022/06/28 22:37
 */
@RestController
@RequestMapping("wx/")
public class WXAuthController {


    @PostMapping("auth/login")
    public BaseRespVo login(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        //在这里要执行登录，Subject.login
        // → Subject只能作为局部变量，不能作为全局变量
        Subject subject = SecurityUtils.getSubject();
        // 执行登录（认证）
        //UsernamePasswordToken authenticationToken = new UsernamePasswordToken(username,password);
        MarketToken authenticationToken = new MarketToken(username, password, "wx");
        try {
            // realm.doGetAuthenticationInfo
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            return BaseRespVo.invalidAuth("用户名或密码不正确");
//            e.printStackTrace();
        }

        // 判断是否认证成功
        boolean authenticated = subject.isAuthenticated();

        // 可以获得session信息，也可以获得其sessionId
        Session session = subject.getSession();

        session.setAttribute("username", username);
        // 获得Principal信息 → realm的doGetAuthorizationInfo方法构造的认证信息里的第一个参数
        MarketUser primaryPrincipal = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();


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


    @PostMapping("auth/logout")
    public BaseRespVo logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok();
    }

    @GetMapping("user/index")
    public BaseRespVo userIndex() {
        return null;
    }

}
