package com.cskaoyan.bean.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 17:27:36
 * @Description: 订单首页
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderListBO {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    List<Date> timeArray;
    List<Short> orderStatusArray;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date end;
    Integer userId;
    String orderSn;

}
