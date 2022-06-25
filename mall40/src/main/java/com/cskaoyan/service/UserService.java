package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;

public interface UserService {
    CommonData<User> query(String username, BasePageInfo info);
}
