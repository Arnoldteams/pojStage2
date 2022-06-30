package com.cskaoyan.controller;

import com.cskaoyan.bean.AdminInfoBean;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.LoginUserData;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.WxAuthRegisterBO;
import com.cskaoyan.bean.vo.WxAuthRegisterVO;
import com.cskaoyan.configuration.realm.MarketToken;
import com.cskaoyan.service.FileService;
import com.cskaoyan.service.WxAuthorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xyg2597@163.com
 * @since 2022/06/28 22:37
 */
@RestController
@RequestMapping("wx/auth")
public class WxAuthController {

    @Autowired
    WxAuthorService wxAuthorService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    FileService fileService;

    @Autowired
    HttpServletRequest request;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-30 08:13:25
     * @description: 重置密码
     * @param: wxAuthRegisterBO - [WxAuthRegisterBO]
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("reset")
    public BaseRespVo reset(@RequestBody WxAuthRegisterBO wxAuthRegisterBO) {

        // get code from redis
        Object code = redisTemplate.opsForValue().get("code");
        String uCode = wxAuthRegisterBO.getCode();

        Boolean codeError = true;
        // 判断验证码是否过期
        if (code != null) {
            // 仅当用户验证码不空，且与验证码相同，false 通过
            if (!StringUtils.isEmpty(uCode) && StringUtils.equals((String) code, uCode)) {
                codeError = false;
            }
        }

        // true means update failed , caused by no phone num
        Boolean failedUpdate = wxAuthorService.updateUserByMobil(wxAuthRegisterBO);

        if (codeError) {
            return BaseRespVo.codeAndMsg(703, "验证码错误，淦");
        }
        if (failedUpdate) {
            return BaseRespVo.codeAndMsg(706, "手机号未注册，宝");
        }

        return BaseRespVo.ok();
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 23:29:53
     * @description: 发送验证码
     * @param: map - [String]
     * @return: com.cskaoyan.bean.BaseRespVo
     */

    @PostMapping("regCaptcha")
    public BaseRespVo regCaptcha(@RequestBody Map<String, String> map) {

        // generate code and put into redis
        String mobile = map.get("mobile");
        String code = RandomStringUtils.randomNumeric(6);
        redisTemplate.opsForValue().set("code", code, 300, TimeUnit.SECONDS);

        // send msg
        fileService.sendMsg(mobile, code);

        return BaseRespVo.ok();
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 23:30:35
     * @description: 注册
     * @param: wxAuthRegisterBO - [WxAuthRegisterBO]
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("register")
    public BaseRespVo register(@RequestBody WxAuthRegisterBO wxAuthRegisterBO) {

        // get code from redis
        Object code = redisTemplate.opsForValue().get("code");
        String uCode = wxAuthRegisterBO.getCode();

        Boolean codeError = true;
        // 判断验证码是否过期
        if (code != null) {
            // 仅当用户验证码不空，且与验证码相同，false 通过
            if (!StringUtils.isEmpty(uCode) && StringUtils.equals((String) code, uCode)) {
                codeError = false;
            }
        }

        // true means has account
        Boolean hasAccount = wxAuthorService.hasAccount(wxAuthRegisterBO.getMobile());

        if (codeError) {
            return BaseRespVo.codeAndMsg(703, "验证码错误，淦");
        }
        if (hasAccount) {
            return BaseRespVo.codeAndMsg(705, "手机号已注册，宝");
        }

        // 初始头像
        String avatarUrl = "https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64";

        // 插入数据
        wxAuthorService.insertUser(wxAuthRegisterBO, avatarUrl, request);

        // 跳转登陆授权
        MarketToken authenticationToken = new MarketToken(wxAuthRegisterBO.getUsername(), wxAuthRegisterBO.getPassword(), "wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            // realm.doGetAuthenticationInfo
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            return BaseRespVo.invalidAuth("用户名或密码不正确");
        }

        // 整合响应的请求
        String sessionId = request.getSession().getId();
        WxAuthRegisterVO.UserInfo userInfo = new WxAuthRegisterVO.UserInfo();
        userInfo.setNickName(wxAuthRegisterBO.getUsername());
        userInfo.setAvatarUrl(avatarUrl);
        WxAuthRegisterVO vo = new WxAuthRegisterVO();
        vo.setToken(sessionId);
        vo.setUserInfo(userInfo);

        return BaseRespVo.ok(vo);

    }


    /**
     * 用户登录
     *
     * @param map
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 20:27
     */
    @PostMapping("login")
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

    /**
     * 用户登出
     *
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 20:27
     */
    @PostMapping("logout")
    public BaseRespVo logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok();
    }

}
