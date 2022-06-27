package com.cskaoyan.bean.vo.statForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 文陶
 * @createTime: 2022年06月27日 10:03:14
 * @version:
 * @Description: 商品统计rows
 */
@Data
public class GoodsRowsEntity {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Double amount;
    private Integer orders;
    private Date day;
    private Integer products;
}
