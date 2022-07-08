package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.BasePageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 23:51:08
 * @Description: 个人-我的订单
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOrderListVO {
    BasePageInfo basePageInfo;
    List<WxOrderListChildVO> list;
}
