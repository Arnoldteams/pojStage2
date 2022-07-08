package com.cskaoyan.bean.bo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:41:57
 * @Description: 提交订单
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOrderSubmitBO {
    Integer addressId;
    Integer cartId;
    Integer couponId;
    Integer grouponLinked;
    Integer grouponRulesId;
    String message;
    Integer userCouponId;
}
