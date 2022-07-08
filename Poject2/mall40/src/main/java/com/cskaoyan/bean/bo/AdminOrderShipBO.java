package com.cskaoyan.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 11:13:18
 * @Description: 商场管理-订单管理-发货
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderShipBO {
    Integer orderId;
    String shipChannel;
    String shipSn;
}
