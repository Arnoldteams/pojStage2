package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.AdminOrderListBO;
import com.cskaoyan.bean.bo.AdminOrderRefundBO;
import com.cskaoyan.bean.bo.AdminOrderReplyBO;
import com.cskaoyan.bean.bo.AdminOrderShipBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOrderDetailVO;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import com.cskaoyan.mapper.MarketCommentMapper;
import com.cskaoyan.mapper.MarketOrderGoodsMapper;
import com.cskaoyan.mapper.MarketOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    @Autowired
    MarketCommentMapper marketCommentMapper;

    @Autowired
    HttpSession session;

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
        if (adminOrderListBO.getStart() != null && adminOrderListBO.getEnd() != null
                && !"".equals(adminOrderListBO.getStart()) && !"".equals(adminOrderListBO.getEnd())) {
            criteria.andAddTimeBetween(adminOrderListBO.getStart(), adminOrderListBO.getEnd());
        }
        if (adminOrderListBO.getOrderStatusArray() != null && !"".equals(adminOrderListBO.getOrderStatusArray())) {
            criteria.andOrderStatusIn(adminOrderListBO.getOrderStatusArray());
        }
        if (adminOrderListBO.getUserId() != null && !"".equals(adminOrderListBO.getUserId())) {
            criteria.andUserIdEqualTo(adminOrderListBO.getUserId());
        }
        if (adminOrderListBO.getOrderSn() != null && !"".equals(adminOrderListBO.getOrderSn())) {
            criteria.andOrderSnEqualTo(adminOrderListBO.getOrderSn().trim());
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
        session.setAttribute("log", String.valueOf(adminOrderShipBO.getShipSn()));
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
        List<AdminOrderDetailGoodsVO> orderGoods = marketOrderGoodsMapper.selectOrderGoods(id);
        AdminOrderDetailVO adminOrderDetailVO = new AdminOrderDetailVO(marketOrder, orderGoods, marketUser);
        return adminOrderDetailVO;
    }

    //判断id是否存在
    @Override
    public List<Integer> queryAllOrderId() {
        List<Integer> orderIdList=marketOrderMapper.selectAllOrderId();
        return orderIdList;
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/27 22:12:26
     * @description: 商场管理-订单管理-退款
     * @param: adminOrderRefundBO
     * @return: void
     */
    @Override
    public void refundOrderMoney(AdminOrderRefundBO adminOrderRefundBO) {
        marketOrderMapper.updateOrderRefund(adminOrderRefundBO);
        session.setAttribute("log", String.valueOf(adminOrderRefundBO.getOrderId()));
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/28 14:22:15
     * @description: 商品管理-商品评论-回复
     * @param: commentId
     * @return: java.lang.String
     */
    @Override
    public String queryAdminComment(Integer commentId) {
        String adminComment = marketOrderMapper.selectAdminComment(commentId);
        return adminComment;
    }

    @Override
    public void replyOrderComment(AdminOrderReplyBO adminOrderReplyBO) {
        marketOrderMapper.updateOrderComment(adminOrderReplyBO);
        session.setAttribute("log", String.valueOf(adminOrderReplyBO.getCommentId()));
    }


}
