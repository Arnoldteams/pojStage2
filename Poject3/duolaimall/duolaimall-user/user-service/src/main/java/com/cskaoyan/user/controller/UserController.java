package com.cskaoyan.user.controller;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.user.constants.UserRetCode;
import com.cskaoyan.user.dal.entitys.Member;
import com.cskaoyan.user.dal.persistence.MemberMapper;
import com.cskaoyan.user.dto.UserLoginRequest;
import com.cskaoyan.user.dto.UserLoginResponse;
import com.cskaoyan.user.dto.UserLoginVertifyDto;
import com.cskaoyan.user.dto.UserTokenDto;
import com.cskaoyan.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    MemberMapper memberMapper;

    @PostMapping("/user/login")
    public ResponseData userLogin( @RequestBody Map map){
        String userName = (String)map.get("userName");
        String userPwd = (String)map.get("userPwd");

        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(userName);
        request.setPassword(userPwd);
        UserLoginResponse response = iUserService.userLogin(request);
        if (UserRetCode.SUCCESS.getCode().equals(response.getCode())){
            return new ResponseUtil().setData(response);
        }

        return new ResponseUtil().setErrorMsg(response.getMsg());

    }

    @GetMapping("/user/login")
    public ResponseData userLoginVertify(HttpRequest request){
        Exception exception = new Exception();

        try {
            HttpHeaders headers = request.getHeaders();
            List<String> userInfos = headers.get("user_info");
            String userInfoJson = userInfos.get(0);
            UserTokenDto userTokenDto = JSON.parseObject(userInfoJson, UserTokenDto.class);
            Member member = memberMapper.selectByPrimaryKey(userTokenDto.getUid());
            UserLoginVertifyDto userLoginVertifyDto = new UserLoginVertifyDto();
            userLoginVertifyDto.setUid(member.getId());
            userLoginVertifyDto.setFile(member.getFile());
            return new ResponseUtil().setData(userLoginVertifyDto);
        }catch (Exception e){
            exception=e;
        }
        return new ResponseUtil().setErrorMsg(exception.getMessage());

    }
}
