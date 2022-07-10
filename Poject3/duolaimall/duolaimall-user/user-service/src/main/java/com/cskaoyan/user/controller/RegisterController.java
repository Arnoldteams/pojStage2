package com.cskaoyan.user.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.commons.util.CookieUtil;
import com.cskaoyan.user.constants.UserRetCode;
import com.cskaoyan.user.dto.KaptchaCodeRequest;
import com.cskaoyan.user.dto.KaptchaCodeResponse;
import com.cskaoyan.user.dto.UserRegisterRequest;
import com.cskaoyan.user.dto.UserRegisterResponse;
import com.cskaoyan.user.service.IKaptchaService;
import com.cskaoyan.user.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.Map;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-10 15:15:33
 * @version:
 * @Description:
 */

@RestController
public class RegisterController {

    @Autowired
    IKaptchaService kaptchaService;

    @Autowired
    IRegisterService registerService;

    @PostMapping("user/register")
    public ResponseData userRegister(@RequestBody Map map , HttpServletRequest request) {
        String userName = (String) map.get("userName");
        String userPwd = (String) map.get("userPwd");
        String captCha = (String) map.get("captcha");
        String email = (String) map.get("email");

        //校验验证码
        KaptchaCodeRequest Krequest = new KaptchaCodeRequest();
        String uuid = CookieUtil.getCookieValue(request, "kaptcha_uuid");
        Krequest.setUuid(uuid);
        // 用户输入的验证码
        Krequest.setCode(captCha);
        KaptchaCodeResponse Kresponse = kaptchaService.validateKaptchaCode(Krequest);
        if (!(Kresponse.getCode().equals(UserRetCode.SUCCESS.getCode()))) {
            return new ResponseUtil<>().setErrorMsg(UserRetCode.KAPTCHA_CODE_ERROR.getMessage());
        }


        //在两张表里面插入数据
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterRequest.setUserName(userName);
        userRegisterRequest.setUserPwd(userPwd);
        userRegisterRequest.setEmail(email);


        userRegisterResponse = registerService.userRegister(userRegisterRequest);

        if (UserRetCode.SUCCESS.getCode().equals(userRegisterResponse.getCode())) {
            return new ResponseUtil().setData(null);
        }
        return new ResponseUtil().setErrorMsg(userRegisterResponse.getMsg());
    }



}
