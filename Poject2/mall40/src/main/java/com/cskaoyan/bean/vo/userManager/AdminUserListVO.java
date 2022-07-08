package com.cskaoyan.bean.vo.userManager;

import lombok.Data;

import java.util.List;

@Data
public class AdminUserListVO {
    private Integer total;
    private Integer pages;
    private Integer limit;
    private Integer page;
    private List<UserEntity> list;
}
