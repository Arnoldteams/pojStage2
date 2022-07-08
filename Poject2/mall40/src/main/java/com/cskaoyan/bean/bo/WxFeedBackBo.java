package com.cskaoyan.bean.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 07:44:25
 * @version:
 * @Description:
 */
@Data
public class WxFeedBackBo {
    String mobile;
    String feedType;
    String content;
    Boolean hasPicture;
    String[] picUrls;
}
