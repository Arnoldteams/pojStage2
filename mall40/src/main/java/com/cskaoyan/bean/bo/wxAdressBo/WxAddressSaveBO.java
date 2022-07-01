package com.cskaoyan.bean.bo.wxAdressBo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Author: 文陶
 * @createTime: 2022年06月30日 10:10:33
 * @version:
 * @Description: 小程序保存用户地址修改
 */

@Data

public class WxAddressSaveBO {


    private String areaCode;
    private Boolean isDefault;
    private String addressDetail;
    private String province;
    private String city;
    @NotBlank(message = "姓名不能为空")
    private String name;
    private String county;

    private String mobile;

    @Length(min = 11, max = 11, message = "手机号码长度不正确")
    private String tel;
    private Integer id;

}
