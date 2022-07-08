package com.cskaoyan.service;

import com.cskaoyan.bean.vo.statForm.AdminStatGoodsVO;
import com.cskaoyan.bean.vo.statForm.AdminStatOrderVO;
import com.cskaoyan.bean.vo.statForm.AdminStatUserVO;

public interface AdminStatService {
    /**
     * @author: 文陶
     * @createTime: 2022/06/26 23:07:18
     * @description: 用户统计
     * @param:  null
     * @return: com.cskaoyan.bean.vo.statForm.AdminStatUserVO
     */
    AdminStatUserVO statUser();

    /**
     * @author: 文陶
     * @createTime: 2022/06/27 01:39:20
     * @description: 订单统计
     * @param:  null
     * @return: com.cskaoyan.bean.vo.statForm.AdminStatOrderVO
     */
    AdminStatOrderVO statOrder();

    /**
     * @author: 文陶
     * @createTime: 2022/06/27 10:11:10
     * @description: 商品统计
     * @param:  null
     * @return: com.cskaoyan.bean.vo.statForm.AdminStatGoodsVO
     */
    AdminStatGoodsVO statGoods();

}
