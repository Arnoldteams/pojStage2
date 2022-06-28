package com.cskaoyan.bean.vo.statForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 文陶
 * @createTime: 2022年06月27日 01:32:51
 * @version:
 * @Description: 订单rows
 */

@Data
public class OrderRowsEntity {
    private double amount;
    private Integer orders;
    private Integer customers;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date day;
    private double pcr;
}
