package com.cskaoyan.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 微信评论页面
 *
 * @author sprinkle
 * @since 2022/06/29 20:35
 */
@Data
public class WxCommentVO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String adminContent;

    private String content;

    private String[] picList;

    private UserInfo userInfo;
}
