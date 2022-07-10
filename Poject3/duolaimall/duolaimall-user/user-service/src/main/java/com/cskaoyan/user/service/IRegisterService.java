package com.cskaoyan.user.service;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.user.dto.UserRegisterRequest;
import com.cskaoyan.user.dto.UserRegisterResponse;

import javax.xml.bind.ValidationException;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-10 15:22:02
 * @version:
 * @Description:
 */
public interface IRegisterService {

    UserRegisterResponse userRegister(UserRegisterRequest userRegisterRequest);
}
