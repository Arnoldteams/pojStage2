package com.cskaoyan.service;

import com.cskaoyan.bean.User;

import java.util.List;

public interface UserService {
    public Integer insertUser(User user);

    public List<User> queryUsersByLike(String content);
}
