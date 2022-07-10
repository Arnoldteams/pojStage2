package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.mall.dto.*;
import com.cskaoyan.shopping.converter.ContentConverter;
import com.cskaoyan.shopping.converter.ProductConverter;
import com.cskaoyan.shopping.dal.entitys.Item;
import com.cskaoyan.shopping.dal.entitys.ItemDesc;
import com.cskaoyan.shopping.dal.entitys.Panel;
import com.cskaoyan.shopping.dal.entitys.PanelContentItem;
import com.cskaoyan.shopping.dal.persistence.ItemDescMapper;
import com.cskaoyan.shopping.dal.persistence.ItemMapper;
import com.cskaoyan.shopping.dal.persistence.PanelContentMapper;
import com.cskaoyan.shopping.dal.persistence.PanelMapper;
import com.cskaoyan.shopping.dto.*;
import com.cskaoyan.shopping.form.PageResponse;
import com.cskaoyan.shopping.service.IProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
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
    ItemDescMapper itemDescMapper;

    @Autowired
    ContentConverter contentConverter;

    @Autowired
    ProductConverter productConverter;

    /***
     * @author: 文陶
     * @createTime: 2022/07/10 20:07:42
     * @description: 查询商品详情
     * @param: request - [null]
     * @return: com.cskaoyan.mall.dto.ProductDetailResponse
     */
    @Override
    public ProductDetailResponse getProductDetail(ProductDetailRequest request) {
        ProductDetailResponse response = new ProductDetailResponse();

        try {
            //参数校验
            request.requestCheck();

            //查询数据
            Item item = itemMapper.selectByPrimaryKey(request.getId());
            ProductDetailDto productDetailDto = productConverter.productDoToDto(item);
            //封装detail，imageBig,imageSmall
            ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(request.getId());
            productDetailDto.setDetail(itemDesc.getItemDesc());
            productDetailDto.setProductImageBig(item.getImageBig());
            List<String> imageSmall = Arrays.asList(item.getImages());
            productDetailDto.setProductImageSmall(imageSmall);

            //成功
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setProductDetailDto(productDetailDto);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }

        return response;
    }


    /***
     * @author: 文陶
     * @createTime: 2022/07/10 20:22:35
     * @description: 获取所有商品
     * @param: request - [null]
     * @return: com.cskaoyan.shopping.dto.AllProductResponse
     */
    @Override
    public AllProductResponse getAllProduct(AllProductRequest request) {
        AllProductResponse response = new AllProductResponse();

        try {
            //参数校验
            request.requestCheck();
            //查询数据
            Example example = new Example(Item.class);
            Example.Criteria criteria = example.createCriteria();
            //按价格排序
            if (request.getSort()!=null&&request.getSort()!=""){
                if ("1".equals(request.getSort())){
                    //升序
                    example.orderBy("price").desc();
                }else{
                    //降序
                    example.orderBy("price").asc();
                }
            }
            //按价格查询
            if (request.getPriceGt()!=null && request.getPriceLte()!=null){
                criteria.andBetween("price",request.getPriceGt(),request.getPriceLte());
            }
            //分页
            PageHelper.startPage(request.getPage(), request.getSize());
            List<Item> items = itemMapper.selectByExample(example);
            PageInfo<Item> itemPageInfo = new PageInfo<>(items);
            List<ProductDto> productDtoList = productConverter.items2Dto(items);
            PageInfo<ProductDto> pageInfo = new PageInfo<>(productDtoList);
            //成功
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setTotal((long) items.size());
            response.setProductDtoList(pageInfo.getList());
        } catch (Exception e) {
            //异常处理
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-07-09 15:39:37
     * @description: 获取推荐商品信息
     * @param:
     * @return: com.cskaoyan.shopping.dto.RecommendResponse
     */
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
