package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;

import java.util.List;

public interface UserService {
    CommonData<User> query(String username, BasePageInfo info);

    AdminUserListVO queryUserList(AdminUserListBO userListBO, BasePageInfo pageInfo);

    UserEntity queryById(Integer id);

    void updateUser(UserEntity user);

//    获取所有操作管理员操作列表,并封装到VO中
    List<MarketLog> getLogList(BaseParam info, String name);
}
