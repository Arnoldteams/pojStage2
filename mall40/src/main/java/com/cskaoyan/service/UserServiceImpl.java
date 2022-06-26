package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stone
 * @date 2022/06/25 11:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public CommonData<User> query(String username, BasePageInfo info) {
        PageHelper.startPage(info.getPage(), info.getLimit());
        if (username != null) {
            username = "%" + username + "%";
        }
        List<User> users = userMapper.select(username, info.getSort(), info.getOrder());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return CommonData.data(pageInfo);
    }

    @Override
    public AdminUserListVO queryUserList(AdminUserListBO userListBO, BasePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        if (userListBO != null) {
            if (userListBO.getUsername() != null) {
                userListBO.setUsername("%" + userListBO.getUsername() + "%");
            }
        }
        List<UserEntity> userList = userMapper.selectUserList(userListBO, pageInfo.getSort(), pageInfo.getOrder());

        PageInfo<UserEntity> pageInfoVo = new PageInfo<UserEntity>(userList);

        AdminUserListVO userListVO = new AdminUserListVO();
        userListVO.setLimit(pageInfoVo.getPageSize());
        userListVO.setList(pageInfoVo.getList());
        userListVO.setPage(pageInfoVo.getPageNum());
        userListVO.setPages(pageInfoVo.getPages());
        userListVO.setTotal((int) pageInfoVo.getTotal());

        return userListVO;
    }

    @Override
    public UserEntity queryById(Integer id) {
        UserEntity user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public void updateUser(UserEntity user) {
        userMapper.updateUser(user);
    }
}
