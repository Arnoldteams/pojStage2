package com.cskaoyan.user.controller;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.commons.util.CookieUtil;
import com.cskaoyan.user.constants.UserRetCode;
import com.cskaoyan.user.dal.entitys.Member;
import com.cskaoyan.user.dal.persistence.MemberMapper;
import com.cskaoyan.user.dto.*;
import com.cskaoyan.user.service.IKaptchaService;
import com.cskaoyan.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-09 13:59:07
 * @version:
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IKaptchaService kaptchaService;




    @PostMapping("/user/login")
    public ResponseData userLogin(@RequestBody Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String userName = (String)map.get("userName");
        String userPwd = (String)map.get("userPwd");
        String captCha = (String)map.get("captcha");


        //验证码校验
        KaptchaCodeRequest Krequest = new KaptchaCodeRequest();
        String uuid = CookieUtil.getCookieValue(httpServletRequest, "kaptcha_uuid");
        Krequest.setUuid(uuid);
        // 用户输入的验证码
        Krequest.setCode(captCha);
        KaptchaCodeResponse Kresponse = kaptchaService.validateKaptchaCode(Krequest);
        if (!(Kresponse.getCode().equals(UserRetCode.SUCCESS.getCode()))) {
            return new ResponseUtil<>().setErrorMsg(UserRetCode.KAPTCHA_CODE_ERROR.getMessage());
        }

        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(userName);
        request.setPassword(userPwd);
        UserLoginResponse response = iUserService.userLogin(request);
        if (UserRetCode.SUCCESS.getCode().equals(response.getCode())){
            //在相应头中添加名为access_token的cookie用来存放jwt并发送给用户浏览器
            Cookie tokenCookie = new Cookie("access_token", response.getToken());
            tokenCookie.setPath("/");
            httpServletResponse.addCookie(tokenCookie);
            return new ResponseUtil().setData(response);
        }

        return new ResponseUtil().setErrorMsg(response.getMsg());

    }

    @GetMapping("/user/login")
    public ResponseData userLoginVertify(HttpServletRequest request){

        String userInfoJson = request.getHeader("user_info");
        if(userInfoJson == null){
            return new ResponseUtil().setErrorMsg("参数值校验失败");
        }

        UserTokenDto userTokenDto = JSON.parseObject(userInfoJson, UserTokenDto.class);

        return new ResponseUtil().setData(userTokenDto);

    }

    @GetMapping("/user/loginOut")
    public ResponseData userLoginOut(HttpServletRequest request,HttpServletResponse response){

        ResponseData responseData = new ResponseData();
        String userInfoJson = request.getHeader("user_info");

        if(userInfoJson == null){
            return new ResponseUtil().setErrorMsg("退出失败");
        }

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("access_token".equals(cookies[i].getName())){
                cookies[i].setMaxAge(0);
                Cookie logOutCookie = new Cookie("access_token", null);
                logOutCookie.setPath("/");
                logOutCookie.setMaxAge(0);
                response.addCookie(logOutCookie);
            }
        }

        return new ResponseUtil().setData(responseData.getResult());

    }


}
