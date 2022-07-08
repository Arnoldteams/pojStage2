package com.cskaoyan.bean;

import lombok.Data;

@Data
public class LoginUserData {

    /**
     * adminInfo : {"nickName":"admin123","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}
     * token : 980d6139-72e9-4f78-8c87-005690a4d778
     */

    private AdminInfoBean adminInfo;
    private String token;

}
