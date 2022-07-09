package com.cskaoyan.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.commons.util.jwt.JwtTokenUtils;
import com.cskaoyan.user.converter.MemberConverter;
import com.cskaoyan.user.dal.entitys.Member;
import com.cskaoyan.user.dal.persistence.MemberMapper;
import com.cskaoyan.user.dto.QueryMemberResponse;
import com.cskaoyan.user.dto.UserLoginRequest;
import com.cskaoyan.user.dto.UserLoginResponse;
import com.cskaoyan.user.dto.UserTokenDto;
import com.cskaoyan.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import com.cskaoyan.user.constants.UserRetCode;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-09 10:20:55
 * @version:
 * @Description:
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberConverter memberConverter;

    @Override
    public UserLoginResponse userLogin(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();

        try {
            request.requestCheck();
            String userName = request.getUserName();
            String passWord = request.getPassword();
            Example example = new Example(Member.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username",userName).andEqualTo("password",passWord);
            Member member = memberMapper.selectOneByExample(example);
            if(member==null){
                response.setCode(UserRetCode.USERORPASSWORD_ERRROR.getCode());
                response.setMsg(UserRetCode.USERORPASSWORD_ERRROR.getMessage());
                throw new Exception("用户名或密码错误");
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
            e.printStackTrace();
        }

        return response;
    }
}
