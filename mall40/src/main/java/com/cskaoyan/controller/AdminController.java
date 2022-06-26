/**
 * @author: 文陶
 * @createTime: 2022/06/26 16:42:07
 * @description: 用户管理
 */
package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;


    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:39:36
     * @description: admin/user/list
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/list")
    public BaseRespVo list(AdminUserListBO userListBO, BasePageInfo pageInfo) {
        AdminUserListVO userListVO = userService.queryUserList(userListBO, pageInfo);
        return BaseRespVo.ok(userListVO);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:34:53
     * @description: admin/user/detail
     * @param: id
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/detail")
    public BaseRespVo detail(Integer id) {
        UserEntity user = userService.queryById(id);
        return BaseRespVo.ok(user);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:38:23
     * @description: admin/user/update
     * @param: user
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/update")
    public BaseRespVo update(@RequestBody UserEntity user){
        userService.updateUser(user);
        return  BaseRespVo.ok(1);
    }
}
