package com.cskaoyan.bean.param;

import lombok.Data;

import java.util.Date;


@Data
public class User {
    Integer id;
    String username;
    String password;
    Integer gender;
    Date birthday;
}
