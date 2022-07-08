package com.cskaoyan.bean.vo.statForm;

import lombok.Data;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月27日 10:01:32
 * @version:
 * @Description: 商品统计
 */

@Data
public class AdminStatGoodsVO {
    private List<String> columns;
    private List<GoodsRowsEntity> rows;
}
