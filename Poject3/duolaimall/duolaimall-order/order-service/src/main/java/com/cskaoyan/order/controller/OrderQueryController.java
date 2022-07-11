package com.cskaoyan.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.order.dto.*;
import com.cskaoyan.order.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @since 2022/07/09 11:11
 * @author Gzy
 */

@RestController
@RequestMapping("/shopping")
public class OrderQueryController {

    @Autowired
    OrderQueryService queryService;

    @GetMapping("/order")
    public ResponseData queryAllOrder(OrderListRequest request, HttpServletRequest httpServletRequest){
        // 拿到用户Id
        String userInfo = httpServletRequest.getHeader("user_info");
        JSONObject jsonObject = JSON.parseObject(userInfo);
        long uid = Long.parseLong(jsonObject.get("uid").toString());
        request.setUserId(uid);

        OrderListResponse response = queryService.orderList(request);
        if(response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())){
            HashMap<Object, Object> res = new HashMap<>();
            res.put("total",response.getTotal());
            res.put("data",response.getDetailInfoList());
            return new ResponseUtil().setData(res);
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());

    }

    /**
     * @author: 极其帅气的 Sssd
     */
    @GetMapping("/order/{id}")
    public ResponseData qurryOrderDetail(@PathVariable("id") String id) {

        OrderDetailRequest request = new OrderDetailRequest();
        request.setOrderId(id);

        // 调用业务层逻辑
        TrueOrderDetailResponse response = queryService.orderDetail(request);

        // 判断返回值
        if (response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            ResponseData<Object> objectResponseData = new ResponseUtil<>().setData(response);
            return objectResponseData;
        }

        return new ResponseUtil<>().setErrorMsg(response.getMsg());
    }
}
