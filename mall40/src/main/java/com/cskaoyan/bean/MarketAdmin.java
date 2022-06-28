package com.cskaoyan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Boolean deleted;

    // 数据库里的列 role_ids → varchar

    // 做查询 varchar → String → Integer[]      输出映射
    // 做新增 Integer[] → String → varchar      输入映射
    // varchar ↔ Integer[]
    // private Integer[] roleIds;
    private Integer[] roleIds;


}