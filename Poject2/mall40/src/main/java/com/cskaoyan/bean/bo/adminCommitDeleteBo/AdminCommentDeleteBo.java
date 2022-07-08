package com.cskaoyan.bean.bo.adminCommitDeleteBo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 22:59
 */
@Data
public class AdminCommentDeleteBo {

    /**
     * hasPicture : true
     * valueId : 1006002
     * deleted : false
     * star : 1
     * addTime : 2018-01-31T16:00:00.000+00:00
     * adminContent :
     * updateTime : 2018-01-31T16:00:00.000+00:00
     * id : 5
     * type : 0
     * picUrls : ["http://yanxuan.nosdn.127.net/d85a556893919038b56a95b71c9a1228.jpg"]
     * userId : 1
     * content : 漂亮
     */
    private boolean hasPicture;
    private int valueId;
    private boolean deleted;
    private int star;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String adminContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private int id;
    private int type;

    private String[] picUrls;
    private int userId;
    private String content;

}
