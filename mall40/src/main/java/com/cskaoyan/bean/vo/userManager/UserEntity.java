package com.cskaoyan.bean.vo.userManager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class UserEntity {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date addTime;
    private String avatar;
    private Boolean deleted;
    private Integer gender;
    private Integer id;
    private String lastLoginIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date lastLoginTime;

    @Length(min = 11, max = 11, message = "手机号码长度不正确")
    private String mobile;
    private String nickname;
    private String password;
    private String sessionKey;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;
    private Integer userLevel;
    private String username;
    private String weixinOpenid;
}
