package com.cskaoyan.mapper;

import com.cskaoyan.bean.param.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> select(@Param("username") String username, @Param("sort") String sort, @Param("order") String order);
}
