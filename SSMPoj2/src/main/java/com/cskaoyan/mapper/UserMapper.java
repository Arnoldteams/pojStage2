package com.cskaoyan.mapper;

import com.cskaoyan.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    Integer insertUser(@Param("user") User user);

    List<User> queryUsersByLike(@Param("content") String content);

}
