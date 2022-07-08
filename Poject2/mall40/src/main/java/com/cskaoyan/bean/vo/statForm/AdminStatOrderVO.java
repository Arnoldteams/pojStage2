package com.cskaoyan.bean.vo.statForm;

import lombok.Data;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月27日 01:31:44
 * @version:
 * @Description: 订单统计
 */
@Data
public class AdminStatOrderVO {

    private List<String> columns;
    private List<OrderRowsEntity> rows;

}
