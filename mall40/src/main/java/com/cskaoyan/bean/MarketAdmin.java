package com.cskaoyan.bean;

import lombok.Data;

import java.util.Date;

@Data
public class MarketAdmin {
    private Integer id;

    private String username;

    private String password;

    private String lastLoginIp;

    private Date lastLoginTime;

    private String avatar;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    // 数据库里的列 role_ids → varchar

    // 做查询 varchar → String → Integer[]      输出映射
    // 做新增 Integer[] → String → varchar      输入映射
    // varchar ↔ Integer[]
    // private Integer[] roleIds;
    private String roleIds;


}