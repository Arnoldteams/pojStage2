package com.cskaoyan.bean.vo.userManager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    private String avatar;
    private Boolean deleted;
    private Integer gender;
    private Integer id;
    private String lastLoginIp;
    private Date lastLoginTime;
    private String mobile;
    private String nickname;
    private String password;
    private String sessionKey;
    private Integer status;
    private Date updateTime;
    private Integer userLevel;
    private String username;
    private String weixinOpenid;
}
