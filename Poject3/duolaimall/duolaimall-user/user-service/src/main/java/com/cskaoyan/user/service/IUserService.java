package com.cskaoyan.user.service;

import com.cskaoyan.user.dto.UserLoginRequest;
import com.cskaoyan.user.dto.UserLoginResponse;

/**
 * @Author: 夏一男
 * @createTime: 2022-07-09 10:12:08
 * @version:
 * @Description: 用户模块
 */
public interface IUserService {

    UserLoginResponse userLogin(UserLoginRequest userLoginRequest);

}
