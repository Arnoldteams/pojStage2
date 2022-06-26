package com.cskaoyan.bean.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 接收从客户端传递的数据
 * @author xyg2597@163.com
 * @since 2022/06/25 23:41
 */

@Data
public class AdminTopicCreateBO {
    private String title;

    private String subtitle;

    private BigDecimal price;

    private String readCount;

    private String picUrl;

    private String goods;

    private String content;


}
