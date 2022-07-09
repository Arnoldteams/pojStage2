package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.HomePageResponse;
import com.cskaoyan.shopping.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sprinkle
 * @since 2022/07/09 11:16
 */
@RestController
public class ShoppingHomepageController {

    @Autowired
    IHomeService iHomeService;

    @GetMapping("/shopping/homepage")
    public ResponseData homepage() {

        HomePageResponse response = iHomeService.homepage();
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            // 执行成功
            return new ResponseUtil().setData(response.getPanelContentItemDtos());
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
