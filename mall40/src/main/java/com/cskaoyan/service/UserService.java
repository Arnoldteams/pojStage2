package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;

public interface UserService {
    CommonData<User> query(String username, BasePageInfo info);

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 17:53:26
     * @description:admin/user/list
     * @return: com.cskaoyan.bean.vo.userManager.AdminUserListVO
     */
    AdminUserListVO queryUserList(AdminUserListBO userListBO, BasePageInfo pageInfo);

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 17:54:06
     * @description: admin/user/detail
     * @param: id - [null]
     * @return: com.cskaoyan.bean.vo.userManager.UserEntity
     */
    UserEntity queryById(Integer id);

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 17:54:30
     * @description: admin/user/update
     * @param: user - [null]
     * @return: void
     */
    void updateUser(UserEntity user);
}
