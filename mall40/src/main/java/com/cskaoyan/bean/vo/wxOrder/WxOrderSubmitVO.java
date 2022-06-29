package com.cskaoyan.bean.vo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:52:43
 * @Description: 提交订单
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOrderSubmitVO {
    Integer grouponLinkId;
    Integer orderId;
}
