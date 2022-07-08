package com.cskaoyan.bean.vo;

import lombok.Data;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 21:31:57
 * @version:
 * @Description:
 */
@Data
public class WxAuthRegisterVO {
    private String token;
    private UserInfo userInfo;

    @Data
    public static class UserInfo{
        private String nickName;
        private String avatarUrl;
    }
}
