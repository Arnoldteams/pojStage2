package com.cskaoyan.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.order.dto.OrderListRequest;
import com.cskaoyan.order.dto.OrderListResponse;
import com.cskaoyan.order.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseData queryAllOrder(@RequestBody OrderListRequest request, HttpServletRequest httpServletRequest){
        // 拿到用户Id
        // String userInfo = httpServletRequest.getHeader("user_info");
        // JSONObject jsonObject = JSON.parseObject(userInfo);
        // long uid = Long.parseLong(jsonObject.get("uid").toString());
        // request.setUserId(uid);
        request.setUserId((long) 71);

        OrderListResponse response = queryService.orderList(request);
        if(response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())){
            return new ResponseUtil().setData(response);
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());

    }
}
