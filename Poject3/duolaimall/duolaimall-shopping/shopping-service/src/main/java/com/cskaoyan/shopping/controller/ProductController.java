package com.cskaoyan.shopping.controller;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.mall.commons.result.ResponseUtil;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.mall.dto.ProductDetailRequest;
import com.cskaoyan.mall.dto.ProductDetailResponse;
import com.cskaoyan.shopping.dto.AllProductRequest;
import com.cskaoyan.shopping.dto.AllProductResponse;
import com.cskaoyan.shopping.dto.RecommendResponse;
import com.cskaoyan.shopping.form.PageInfo;
import com.cskaoyan.shopping.form.PageResponse;
import com.cskaoyan.shopping.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            // ArrayList<RecommendResponse> resultList = new ArrayList<>();
            // resultList.add(response);
            return new ResponseUtil().setData(response.getPanelContentItemDtos());
        }

        // 执行失败
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }


    /***
     * @author: 文陶
     * @createTime: 2022/07/10 20:09:21
     * @description: 查寻商品详情
     * @param: id
     * @return: com.cskaoyan.mall.commons.result.ResponseData
     */
    @GetMapping("product/{id}")
    public ResponseData getProductDetailById(@PathVariable("id") Long id) {
        ProductDetailRequest request = new ProductDetailRequest();
        request.setId(id);

        ProductDetailResponse response = iProductService.getProductDetail(request);

        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return new ResponseUtil().setData(response.getProductDetailDto());
        }

        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

    /***
     * @author: 文陶
     * @createTime: 2022/07/10 20:09:46
     * @description: 查询所有商品
     * @param: null - [null]
     * @return: null
     */
    @GetMapping("goods")
    public ResponseData getGoods(PageInfo pageInfo) {
        AllProductRequest request = new AllProductRequest();
        request.setPage(pageInfo.getPage());
        request.setSort(pageInfo.getSort());
        request.setSize(pageInfo.getSize());
        request.setPriceGt(pageInfo.getPriceGt());
        request.setPriceLte(pageInfo.getPriceLte());
        request.setCid(pageInfo.getCid());

        AllProductResponse response = iProductService.getAllProduct(request);

        if (ShoppingRetCode.SUCCESS.getCode().equals(response.getCode())) {
            PageResponse data = new PageResponse();
            data.setData(response.getProductDtoList());
            data.setTotal(response.getTotal());
            return new ResponseUtil().setData(data);
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

}