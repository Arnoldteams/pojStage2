package com.cskaoyan.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.commons.util.jwt.JwtTokenUtils;
import com.cskaoyan.user.converter.MemberConverter;
import com.cskaoyan.user.dal.entitys.Member;
import com.cskaoyan.user.dal.entitys.UserVerify;
import com.cskaoyan.user.dal.persistence.MemberMapper;
import com.cskaoyan.user.dal.persistence.UserVerifyMapper;
import com.cskaoyan.user.dto.*;
import com.cskaoyan.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;
import com.cskaoyan.user.constants.UserRetCode;
import lombok.extern.slf4j.Slf4j;
import com.cskaoyan.mall.commons.exception.ValidateException;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-09 10:20:55
 * @version:
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberConverter memberConverter;

    @Autowired
    UserVerifyMapper userVerifyMapper;

    @Override
    public UserLoginResponse userLogin(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();

        try {
            request.requestCheck();
            String userName = request.getUserName();
            String passWord = request.getPassword();
            Example example = new Example(Member.class);
            Example.Criteria criteria = example.createCriteria();
            String encodingPassword = DigestUtils.md5DigestAsHex(passWord.getBytes());
            criteria.andEqualTo("username",userName).andEqualTo("password",encodingPassword);
            Member member = memberMapper.selectOneByExample(example);
            if(member==null){

                throw new ValidateException(UserRetCode.USERORPASSWORD_ERRROR.getCode(),UserRetCode.USERORPASSWORD_ERRROR.getMessage());
            }

            if("N".equals(member.getIsVerified())){

                throw new ValidateException(UserRetCode.USER_ISVERFIED_ERROR.getCode(),UserRetCode.USER_ISVERFIED_ERROR.getMessage());
            }
            QueryMemberResponse queryMemberResponse = memberConverter.member2Res(member);
            response = memberConverter.QueryRes2LoginRes(queryMemberResponse);
            response.setCode(UserRetCode.SUCCESS.getCode());
            response.setMsg(UserRetCode.SUCCESS.getMessage());

            //给响应赋Jwt值
            UserTokenDto userTokenDto = new UserTokenDto();
            userTokenDto.setUid(member.getId());
            userTokenDto.setUsername(member.getUsername());
            String userInfo = JSON.toJSONString(userTokenDto);
            String token = JwtTokenUtils.builder().msg(userInfo).build().creatJwtToken();
            response.setToken(token);
        } catch (Exception e) {
            log.error("UserServiceImpl.userLogin occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }

        return response;
    }

    @Override
    public UserVerifyResponse userVerify(UserVerifyRequest userVerifyRequest) {
        UserVerifyResponse response = new UserVerifyResponse();

        try {
            userVerifyRequest.requestCheck();

            //在member表里修改用户的激活状态
            Member member = new Member();
            member.setIsVerified("Y");
            Example example = new Example(Member.class);
            example.createCriteria().andEqualTo("username",userVerifyRequest.getUserName());
            int i = memberMapper.updateByExampleSelective(member, example);
            if (i != 1) {
                response.setMsg(UserRetCode.USERVERIFY_INFOR_INVALID.getMessage());
                response.setCode(UserRetCode.USERVERIFY_INFOR_INVALID.getCode());
                return response;
            }

            //在user_verify里修改用户的激活状态
            UserVerify userVerify = new UserVerify();
            userVerify.setIsVerify("Y");
            Example example1 = new Example(UserVerify.class);
            example1.createCriteria().andEqualTo("uuid",userVerifyRequest.getUuid()).andEqualTo("username",userVerifyRequest.getUserName());
            int rows = userVerifyMapper.updateByExampleSelective(userVerify, example1);
            if (rows != 1) {
                response.setMsg(UserRetCode.USERVERIFY_INFOR_INVALID.getMessage());
                response.setCode(UserRetCode.USERVERIFY_INFOR_INVALID.getCode());
                return response;
            }
        } catch (Exception e) {
            log.error("RegisterServiceImpl.userRegister occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        response.setCode(UserRetCode.SUCCESS.getCode());
        response.setMsg(UserRetCode.SUCCESS.getMessage());
        return response;
    }


}
