package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月27日 00:30:26
 * @Description: 订单详情的回显
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderDetailVO {
    MarketOrder order;
    List<AdminOrderDetailGoodsVO> orderGoods;
    MarketUser user;
}
