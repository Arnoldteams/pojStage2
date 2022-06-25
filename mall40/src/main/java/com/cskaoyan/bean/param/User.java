package com.cskaoyan.bean.param;

import lombok.Data;

import java.util.Date;

/**
 * @author stone
 * @date 2022/06/25 11:17
 */
@Data
public class User {
    Integer id;
    String username;
    String password;
   Integer gender;
    Date birthday;
    Date lastLoginTime;
}
