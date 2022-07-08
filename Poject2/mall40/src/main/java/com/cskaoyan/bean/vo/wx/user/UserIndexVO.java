package com.cskaoyan.bean.vo.wx.user;

import lombok.Data;

/**
 * 返回给小程序的信息
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 15:41
 */
@Data
public class UserIndexVO {
    UserIndexOrder order;

    public static UserIndexVO setUserIndexVO(UserIndexOrder userIndexOrder){
        UserIndexVO userIndexVO = new UserIndexVO();
        UserIndexOrder order = new UserIndexOrder();
        order.setUncomment(userIndexOrder.getUncomment());
        order.setUnpaid(userIndexOrder.getUnpaid());
        order.setUnrecv(userIndexOrder.getUnrecv());
        order.setUnship(userIndexOrder.getUnship());
        userIndexVO.setOrder(order);
        return userIndexVO;
    }
}
