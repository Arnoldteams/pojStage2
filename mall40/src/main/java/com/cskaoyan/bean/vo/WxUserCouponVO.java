package com.cskaoyan.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

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
    Boolean available;
}
