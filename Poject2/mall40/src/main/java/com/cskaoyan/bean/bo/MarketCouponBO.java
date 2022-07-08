package com.cskaoyan.bean.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-26 20:27:00
 * @version:
 * @Description:
 */
@Data
public class MarketCouponBO {
    private Integer id;

    private String name;

    private String desc;

    private String tag;

    private Integer total;

    private BigDecimal discount;

    private BigDecimal min;

    private Short limit;

    private Short type;

    private Short status;

    private Short goodsType;

    private String[] goodsValue;

    private String code;

    private Short timeType;

    private Short days;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date endTime;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}
