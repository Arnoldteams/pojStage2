package com.cskaoyan.service;

import com.cskaoyan.bean.User;
import com.cskaoyan.mapper.AccountMapper;
import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月18日 15:35:11
 * @version:
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer insertUser(User user) {

        userMapper.insertUser(user);

        return user.getId();
    }

    @Override
    public List<User> queryUsersByLike(String content) {

        List<User> users = userMapper.queryUsersByLike(content);

        return users;
    }
}
