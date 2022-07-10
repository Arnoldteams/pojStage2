package com.cskaoyan.user.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.cskaoyan.user.constants.UserRetCode;
import com.cskaoyan.user.dal.entitys.Member;
import com.cskaoyan.user.dal.entitys.UserVerify;
import com.cskaoyan.user.dal.persistence.MemberMapper;
import com.cskaoyan.user.dal.persistence.UserVerifyMapper;
import com.cskaoyan.user.dto.UserRegisterRequest;
import com.cskaoyan.user.dto.UserRegisterResponse;
import com.cskaoyan.user.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-10 15:26:14
 * @version:
 * @Description:
 */

@Service
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    UserVerifyMapper userVerifyMapper;

    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest) throws ValidationException {
        UserRegisterResponse response = new UserRegisterResponse();
        //判空
        userRegisterRequest.requestCheck();

        //验证用户名是否重复
        validateRepeat(userRegisterRequest);

        //在用户表(member)里插入一条数据
        Member member = new Member();
        member.setUsername(userRegisterRequest.getUserName());
        member.setEmail(userRegisterRequest.getEmail());

        //给用户的密码加密
        String passWord = DigestUtils.md5DigestAsHex(userRegisterRequest.getUserPwd().getBytes());
        member.setPassword(passWord);
        member.setCreated(new Date());
        member.setUpdated(new Date());
        member.setState(1);
        member.setIsVerified("N");
        int i = memberMapper.insertSelective(member);
        if(i != 1){
            response.setMsg(UserRetCode.USER_REGISTER_FAILED.getMessage());
            response.setCode(UserRetCode.USER_REGISTER_FAILED.getCode());
            return response;
        }

        //在用户验证表里插入一条数据
        UserVerify userVerify = new UserVerify();
        userVerify.setUsername(userRegisterRequest.getUserName());
        userVerify.setRegisterDate(new Date());
        String key =userRegisterRequest.getUserName()+userRegisterRequest.getUserPwd()+ UUID.randomUUID().toString();
        String uuid = DigestUtils.md5DigestAsHex(key.getBytes());
        userVerify.setUuid(uuid);
        userVerify.setIsVerify("N");
        userVerify.setIsExpire("N");
        int rows = userVerifyMapper.insertSelective(userVerify);
        if(rows != 1){
            response.setMsg(UserRetCode.USER_REGISTER_FAILED.getMessage());
            response.setCode(UserRetCode.USER_REGISTER_FAILED.getCode());
            return response;
        }

        //发送用户激活邮件

        return null;
    }

    private void validateRepeat(UserRegisterRequest userRegisterRequest) throws ValidationException {
        Example example = new Example(Member.class);
        example.createCriteria().andEqualTo("username",userRegisterRequest.getUserName());
        Member member = memberMapper.selectOneByExample(example);
        if(member != null){
            throw new ValidationException(UserRetCode.USERNAME_ALREADY_EXISTS.getCode(),UserRetCode.USERNAME_ALREADY_EXISTS.getMessage());
        }
    }
}
