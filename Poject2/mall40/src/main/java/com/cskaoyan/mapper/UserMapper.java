package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> select(@Param("username") String username, @Param("sort") String sort, @Param("order") String order);

    List<UserEntity> selectUserList(@Param("user") AdminUserListBO userListBO, @Param("sort") String sort, @Param("order") String order);

    UserEntity selectUserById(@Param("id") Integer id);

    void updateUser(@Param("user") UserEntity user);
}
