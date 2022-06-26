package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;

public interface UserService {
    CommonData<User> query(String username, BasePageInfo info);

    AdminUserListVO queryUserList(AdminUserListBO userListBO, BasePageInfo pageInfo);

    UserEntity queryById(Integer id);

    void updateUser(UserEntity user);
}
