package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.bo.AdminOrderListBO;
import com.cskaoyan.bean.bo.AdminOrderShipBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOrderDetailVO;


public interface AdminOrderService {

    CommonData<MarketOrder> queryOrderList(BasePageInfo basePageInfo, AdminOrderListBO adminOrderListBO);

    void changeOrderStatus(AdminOrderShipBO adminOrderShipBO);

    AdminOrderDetailVO detailOrderList(Integer id);



}
