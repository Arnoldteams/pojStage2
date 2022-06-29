package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.bo.wxOrder.WxOrderListHandleOption;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 23:34:28
 * @Description: 个人-我的订单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOrderListChildVO {
     BigDecimal actualPrice;
     Short aftersaleStatus;
     List<AdminOrderDetailGoodsVO> goodsList;
     WxOrderListHandleOption handleOption;
     Integer id;
     Boolean isGroupin;
     String orderSn;
     String orderStatusText;


}
