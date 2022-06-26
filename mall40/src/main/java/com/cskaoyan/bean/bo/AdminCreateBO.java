package com.cskaoyan.bean.bo;

import lombok.Data;

/**
 * 获得浏览器中创建管理员的信息
 *
 * @author xyg2597@163.com
 * @since 2022/06/26 17:00
 */
@Data
public class AdminCreateBO {
    Integer id;
    String username;
    String password;
    String avatar;
    Integer[] roleIds;


}
