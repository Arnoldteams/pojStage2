package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.mall.dto.AllItemResponse;
import com.cskaoyan.mall.dto.ProductDetailRequest;
import com.cskaoyan.mall.dto.ProductDetailResponse;
import com.cskaoyan.shopping.converter.ContentConverter;
import com.cskaoyan.shopping.dal.entitys.Item;
import com.cskaoyan.shopping.dal.entitys.Panel;
import com.cskaoyan.shopping.dal.entitys.PanelContentItem;
import com.cskaoyan.shopping.dal.persistence.ItemMapper;
import com.cskaoyan.shopping.dal.persistence.PanelContentMapper;
import com.cskaoyan.shopping.dal.persistence.PanelMapper;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 于艳帆
 * @createTime: 2022年07月09日 13:39:54
 * @version:
 * @Description:
 */
@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    PanelMapper panelMapper;

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
    public ProductDetailResponse getProductDetail(ProductDetailRequest request) {
        return null;
    }

    @Override
    public AllProductResponse getAllProduct(AllProductRequest request) {
        return null;
    }

    @Override
    public RecommendResponse getRecommendGoods() {

        Integer recommendPanelId = 6;

        RecommendResponse response = new RecommendResponse();

        try {
            // 查询数据库得到Do对象
            Panel panel = panelMapper.selectByPrimaryKey(recommendPanelId);
            List<PanelContentItem> panelContentItems = panelContentMapper.selectPanelContentAndProductWithPanelId(recommendPanelId);

            // Do -> Dto
            PanelDto panelDto = contentConverter.panel2Dto(panel);
            List<PanelContentItemDto> panelContentItemDtos = contentConverter.panelContentItem2Dto(panelContentItems);

            // 封装对象
            panelDto.setPanelContentItems(panelContentItemDtos);
            Set<PanelDto> setPanelContentItemDtoSet = new HashSet<>();
            setPanelContentItemDtoSet.add(panelDto);

            // 封装响应
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setPanelContentItemDtos(setPanelContentItemDtoSet);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        return response;
    }

    @Override
    public AllItemResponse getAllItems() {
        return null;
    }
}
