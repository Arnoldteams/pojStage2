package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stone
 * @date 2022/06/25 11:11
 */
@Service
public class UserServiceImpl implements UserService{

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
}
