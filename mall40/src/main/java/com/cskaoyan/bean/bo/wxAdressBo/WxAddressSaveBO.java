package com.cskaoyan.bean.bo.wxAdressBo;

import lombok.Data;

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
    private String name;
    private String county;
    private String tel;
    private Integer id;

}
