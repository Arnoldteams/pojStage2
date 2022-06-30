package com.cskaoyan.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-29 17:15:10
 * @version:
 * @Description: 优惠券
 */

@Data
public class WxUserCouponVO {
    Integer id;
    Integer cid;
    String name;
    String desc;
    String tag;
    BigDecimal min;
    BigDecimal discount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date endTime;
    Boolean available = true;
}
