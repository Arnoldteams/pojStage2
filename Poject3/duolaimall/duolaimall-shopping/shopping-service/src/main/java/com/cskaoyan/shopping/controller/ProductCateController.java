package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.dto.AllProductCateRequest;
import com.cskaoyan.shopping.dto.AllProductCateResponse;
import com.cskaoyan.shopping.service.IProductCateService;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 于艳帆
 * @createTime: 2022年07月09日 13:36:41
 * @version:
 * @Description:
 */

@RestController
@RequestMapping("shopping")
public class ProductCateController {
    @Autowired
    IProductCateService productCateService;



    @GetMapping("categories")
    public ResponseData getCateGories(){
        AllProductCateRequest request = new AllProductCateRequest();
//        request.setSort(sort);
        AllProductCateResponse response = productCateService.getAllProductCate(request);
        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())){
            //成功
            return new ResponseUtil().setData(response.getProductCateDtoList());
        }
        //失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

}
