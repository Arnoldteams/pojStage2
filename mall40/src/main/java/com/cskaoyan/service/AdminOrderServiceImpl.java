package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.AdminOrderListBO;
import com.cskaoyan.bean.bo.AdminOrderShipBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.AdminOrderDetailVO;
import com.cskaoyan.mapper.MarketOrderGoodsMapper;
import com.cskaoyan.mapper.MarketOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 13:18:09
 * @Description: 商场管理-订单管理
 */

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    MarketOrderMapper marketOrderMapper;

    @Autowired
    MarketOrderGoodsMapper marketOrderGoodsMapper;

    /**
     * @author: ZY
     * @createTime: 2022/06/27 12:48:50
     * @description: 商场管理-订单管理-首页
     * @param: basePageInfo
     * @param: adminOrderListBO
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketOrder>
     */
    @Override
    public CommonData<MarketOrder> queryOrderList(BasePageInfo basePageInfo, AdminOrderListBO adminOrderListBO) {
        PageHelper.startPage(basePageInfo.getPage(), basePageInfo.getLimit());
        MarketOrderExample example = new MarketOrderExample();
        MarketOrderExample.Criteria criteria = example.createCriteria();
        if (adminOrderListBO.getStart() != null && adminOrderListBO.getEnd() != null) {
            criteria.andAddTimeBetween(adminOrderListBO.getStart(), adminOrderListBO.getEnd());
        }
        if (adminOrderListBO.getOrderStatusArray() != null) {
            criteria.andOrderStatusIn(adminOrderListBO.getOrderStatusArray());
        }
        if (adminOrderListBO.getUserId() != null) {
            criteria.andUserIdEqualTo(adminOrderListBO.getUserId());
        }
        if (adminOrderListBO.getOrderSn() != null) {
            criteria.andOrderSnEqualTo(adminOrderListBO.getOrderSn());
        }
        example.setOrderByClause(basePageInfo.getSort() + " " + basePageInfo.getOrder());
        List<MarketOrder> adminOrderListVOList = marketOrderMapper.selectByExample(example);
        PageInfo<MarketOrder> adminOrderListVOPageInfo = new PageInfo<>(adminOrderListVOList);
        return CommonData.data(adminOrderListVOPageInfo);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/27 09:07:54
     * @description: 商场管理-订单管理-发货
     * @param: adminOrderShipBO
     * @return: void
     */
    @Override
    public void changeOrderStatus(AdminOrderShipBO adminOrderShipBO) {
        marketOrderMapper.updateOrderStatus(adminOrderShipBO);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/27 09:08:43
     * @description:商场管理-订单管理-订单详情
     * @param: id
     * @return: com.cskaoyan.bean.vo.AdminOrderDetailVO
     */
    @Override
    public AdminOrderDetailVO detailOrderList(Integer id) {
        MarketOrder marketOrder = marketOrderMapper.selectOrder(id);
        MarketUser marketUser = marketOrderMapper.selectUser(id);

        MarketOrderGoodsExample example = new MarketOrderGoodsExample();
        MarketOrderGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(id);
        List<MarketOrderGoods> orderGoods = marketOrderGoodsMapper.selectByExample(example);

        AdminOrderDetailVO adminOrderDetailVO = new AdminOrderDetailVO(marketOrder, orderGoods, marketUser);
        return adminOrderDetailVO;

    }




}
