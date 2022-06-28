package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.bo.AdminOrderListBO;
import com.cskaoyan.bean.bo.AdminOrderRefundBO;
import com.cskaoyan.bean.bo.AdminOrderReplyBO;
import com.cskaoyan.bean.bo.AdminOrderShipBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOrderChannelVO;
import com.cskaoyan.bean.vo.AdminOrderDetailVO;
import com.cskaoyan.service.AdminOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 13:14:41
 * @Description: 商场管理-订单管理
 */

@RestController
@RequestMapping("admin/order")
public class AdminOrderController {

    @Autowired
    AdminOrderService adminOrderService;

    /**
     * @author: ZY
     * @createTime: 2022/06/26 17:14:18
     * @description: 商场管理-订单首页-物流公司
     * @param:
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("channel")
    public BaseRespVo channel() {
        ArrayList<AdminOrderChannelVO> data = new ArrayList<>();
        data.add(new AdminOrderChannelVO("ZTO", "中通快递"));
        data.add(new AdminOrderChannelVO("YTO", "圆通速递"));
        data.add(new AdminOrderChannelVO("YD", "韵达速递"));
        data.add(new AdminOrderChannelVO("YZPY", "邮政快递包裹"));
        data.add(new AdminOrderChannelVO("EMS", "EMS"));
        data.add(new AdminOrderChannelVO("DBL", "德邦快递"));
        data.add(new AdminOrderChannelVO("FAST", "快捷快递"));
        data.add(new AdminOrderChannelVO("ZJS", "宅急送"));
        data.add(new AdminOrderChannelVO("TNT", "TNT快递"));
        data.add(new AdminOrderChannelVO("UPS", "UPS"));
        data.add(new AdminOrderChannelVO("DHL", "DHL"));
        data.add(new AdminOrderChannelVO("FEDEX", "FEDEX联邦（国内件）"));
        data.add(new AdminOrderChannelVO("FEDEX_GJ", "FEDEX联邦（国际件）"));
        return BaseRespVo.ok(data);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/27 08:40:36
     * @description: 商场管理-订单管理-首页订单
     * @param: basePageInfo
     * @param: adminOrderListBO
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("list")
    public BaseRespVo list(BasePageInfo basePageInfo, AdminOrderListBO adminOrderListBO) {
        CommonData<MarketOrder> data = adminOrderService.queryOrderList(basePageInfo, adminOrderListBO);
        return BaseRespVo.ok(data);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/27 08:41:02
     * @description: 商场管理-订单管理-发货
     * @param: adminOrderShipBO
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("ship")
    public BaseRespVo ship(@RequestBody AdminOrderShipBO adminOrderShipBO) {
        if (StringUtils.isEmpty(adminOrderShipBO.getShipChannel()) ||
                StringUtils.isEmpty(adminOrderShipBO.getShipSn())) {
            return BaseRespVo.errParam();
        }
        adminOrderService.changeOrderStatus(adminOrderShipBO);
        return BaseRespVo.ok(null);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/27 08:41:35
     * @description: 商场管理-订单管理-商品详情
     * @param: id
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("detail")
    public BaseRespVo detail(Integer id) {
        AdminOrderDetailVO adminOrderDetailVO = adminOrderService.detailOrderList(id);
        return BaseRespVo.ok(adminOrderDetailVO);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/27 14:54:21
     * @description: 老师给的订单数据都不能删，之后能删再改
     * @param: orderId
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("delete")
    public BaseRespVo delete(Integer orderId) {
        return BaseRespVo.unableDelete();
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/27 21:25:09
     * @description: 商场管理-订单管理-退款
     * @param: orderId
     * @param: refundMoney
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody AdminOrderRefundBO adminOrderRefundBO) {
        adminOrderService.refundOrderMoney(adminOrderRefundBO);
        return BaseRespVo.ok(null);
    }


    /**
    * @author: ZY
    * @createTime: 2022/06/28 14:26:32
    * @description:商品管理-商品评论-回复
    * @param: adminOrderReplyBO
    * @return: com.cskaoyan.bean.BaseRespVo
            */
    @RequestMapping("reply")
    public BaseRespVo reply(@RequestBody AdminOrderReplyBO adminOrderReplyBO) {
        if (!StringUtils.isEmpty(adminOrderService.queryAdminComment(adminOrderReplyBO.getCommentId()))) {
            return BaseRespVo.hasReplied(null);
        }
        if (StringUtils.isEmpty(adminOrderReplyBO.getContent())) {
            return BaseRespVo.errParam();
        }
        adminOrderService.replyOrderComment(adminOrderReplyBO);
        return BaseRespVo.ok(null);
    }

}
