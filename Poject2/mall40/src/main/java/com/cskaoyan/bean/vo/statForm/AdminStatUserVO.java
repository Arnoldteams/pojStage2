package com.cskaoyan.bean.vo.statForm;

import lombok.Data;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 22:53:30
 * @version:
 * @Description: 用户统计
 */
@Data
public class AdminStatUserVO {
    private List<String> columns;
    private List<RowsEntity> rows;

}
