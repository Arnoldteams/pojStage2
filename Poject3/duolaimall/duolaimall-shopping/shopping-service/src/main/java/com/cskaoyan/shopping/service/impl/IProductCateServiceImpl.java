package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.converter.ProductCateConverter;
import com.cskaoyan.shopping.dal.entitys.ItemCat;
import com.cskaoyan.shopping.dal.persistence.ItemCatMapper;
import com.cskaoyan.shopping.dto.AllProductCateRequest;
import com.cskaoyan.shopping.dto.AllProductCateResponse;
import com.cskaoyan.shopping.dto.ProductCateDto;
import com.cskaoyan.shopping.service.IProductCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年07月10日 16:08:42
 * @version:
 * @Description: 例举所有商品种类
 */
@Service
public class IProductCateServiceImpl implements IProductCateService {
    @Autowired
    ItemCatMapper itemCatMapper;

    @Autowired
    ProductCateConverter productCateConverter;

    @Override
    public AllProductCateResponse getAllProductCate(AllProductCateRequest request) {

        AllProductCateResponse response = new AllProductCateResponse();

        try {
            //校验参数
            request.requestCheck();
            //获取商品种类数据
            List<ItemCat> itemCats = itemCatMapper.selectAll();
            List<ProductCateDto> productCateDtoList = productCateConverter.items2Dto(itemCats);

            //成功
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setProductCateDtoList(productCateDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }

        return response;
    }
}
