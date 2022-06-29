package com.cskaoyan.bean.bo;

import lombok.Data;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 21:15:31
 * @version:
 * @Description:
 */
@Data
public class WxAuthRegisterBO {

    private String password;
    private String code;
    private String wxCode;
    private String mobile;
    private String username;

}
