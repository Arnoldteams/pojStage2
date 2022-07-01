package com.cskaoyan.bean.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 21:15:31
 * @version:
 * @Description:
 */
@Data
public class WxAuthRegisterBO {


    @Length(min=6,max=16)
    private String password;
    private String code;
    private String wxCode;

    @Length(min=11,max=11)
    @DecimalMin("0")
    private String mobile;
    private String username;

}
