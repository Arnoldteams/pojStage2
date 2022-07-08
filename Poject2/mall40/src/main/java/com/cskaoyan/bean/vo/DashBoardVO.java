package com.cskaoyan.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 14:35:07
 * @Description: 商场首页总量的VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardVO {
    Integer goodsTotal;
    Integer orderTotal;
    Integer productTotal;
    Integer userTotal;
}
