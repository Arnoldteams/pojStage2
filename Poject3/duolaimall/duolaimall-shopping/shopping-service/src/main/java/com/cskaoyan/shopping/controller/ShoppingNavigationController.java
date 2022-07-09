package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.HomePageResponse;
import com.cskaoyan.shopping.dto.NavListResponse;
import com.cskaoyan.shopping.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sprinkle
 * @since 2022/07/09 16:44
 */
@RestController
public class ShoppingNavigationController {

    @Autowired
    IContentService iContentService;

    @GetMapping("/shopping/navigation")
    public ResponseData navigation() {

        NavListResponse response = iContentService.queryNavList();
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            // 执行成功
            return new ResponseUtil().setData(response.getPannelContentDtos());
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
