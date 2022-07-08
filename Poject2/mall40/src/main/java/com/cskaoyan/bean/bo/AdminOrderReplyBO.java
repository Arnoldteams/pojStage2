package com.cskaoyan.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 14:03:28
 * @Description: 商品管理-商品评论-回复
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderReplyBO {
    Integer commentId;
    String content;
}
