package com.cskaoyan.bean.bo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月29日 00:15:01
 * @Description: 提交订单评论时
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOrderListCommentBO {
    String content;
    Boolean hasPicture;
    Integer orderGoodsId;
    String[] picUrls;
    Integer star;

}
