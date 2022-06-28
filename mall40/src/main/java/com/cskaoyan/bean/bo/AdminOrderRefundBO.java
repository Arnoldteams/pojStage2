package com.cskaoyan.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月27日 21:37:59
 * @Description: 商场首页-订单首页-退款
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderRefundBO {
    Integer orderId;
    Integer refundMoney;

}
