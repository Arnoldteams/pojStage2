package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderListCommentBO;
import com.cskaoyan.bean.bo.wxOrder.WxOrderSubmitBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.bean.vo.wxOrder.WxOrderSubmitVO;
import com.cskaoyan.bean.vo.userManager.AdminOrderDetailGoodsVO;
import com.cskaoyan.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: ZY
 * @createTime: 2022年06月28日 19:45:13
 * @Description: 微信-订单
 */

@RestController
@RequestMapping("wx/order")
public class WxOrderController {

    @Autowired
    WxOrderService wxOrderService;


    @RequestMapping("list")
    public BaseRespVo list(Integer showType, Integer page, Integer limit) {
        CommonData<WxOrderListChildVO> data = wxOrderService.queryAllOrder(showType, page, limit);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("cancel")
    public BaseRespVo cancel() {
        return BaseRespVo.ok(null);
    }

    /**
     * @author:ZY
     * @createTime: 2022/06/28 11:05:33
     * @description: 我的订单-订单详情-申请退款
     * @param:
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.refundOrder(orderId);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/28 11:15:33
     * @description: 我的订单-订单详情-退款后可逻辑删除订单
     * @param: map
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.deleteOrder(orderId);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 11:51:13
     * @description: 我的订单-订单详情-订单发货后可确认收货
     * @param: map
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("confirm")
    public BaseRespVo confirm(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.confirmOrder(orderId);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/29 00:12:58
     * @description: 我的订单-订单详情-确认收货后可评价商品
     * @param: orderId
     * @param: goodsId
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("goods")
    public BaseRespVo goods(Integer orderId, Integer goodsId) {
        AdminOrderDetailGoodsVO adminOrderDetailGoodsVO = wxOrderService.queryOrdersGoods(orderId, goodsId);
        return BaseRespVo.ok(adminOrderDetailGoodsVO);
    }

    @RequestMapping("comment")
    public BaseRespVo comment(@RequestBody WxOrderListCommentBO wxOrderListCommentBO) {
        wxOrderService.addOrderComment(wxOrderListCommentBO);
        return BaseRespVo.ok(null);
    }


}
