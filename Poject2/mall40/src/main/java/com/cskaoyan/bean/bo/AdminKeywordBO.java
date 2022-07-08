package com.cskaoyan.bean.bo;

import lombok.Data;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:34:43
 * @version:
 * @Description:
 */
@Data
public class AdminKeywordBO {
    Integer page;
    Integer limit;
    String sort;
    String order;

    String keyword;
    String url;
    Boolean isHot;
    Boolean isDefault;
}
