package com.cskaoyan.bean.vo.statForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 23:00:00
 * @version:
 * @Description: 用户统计rows
 */
@Data
public class RowsEntity {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private Integer users;
}
