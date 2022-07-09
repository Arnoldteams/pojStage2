package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.RecommendResponse;
import com.cskaoyan.shopping.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: 于艳帆
 * @createTime: 2022年07月09日 13:37:17
 * @version:
 * @Description:
 */
@RestController
@RequestMapping("shopping")
public class ProductController {

    @Autowired
    IProductService iProductService;

    /**
     * @author: 于艳帆
     * @createTime: 2022-07-09 13:56:45
     * @description: 获取热门推荐列表
     * @param:
     * @return: com.cskaoyan.mall.commons.result.ResponseData
     */
    @GetMapping("recommend")
    public ResponseData recommend() {

        RecommendResponse response = iProductService.getRecommendGoods();
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            // 执行成功
            ArrayList<RecommendResponse> resultList = new ArrayList<>();
            resultList.add(response);
            return new ResponseUtil().setData(resultList);
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }
}
