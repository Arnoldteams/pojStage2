package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.adminGoodsCreateBo.*;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.AdminGoodsCatAndBrandVo;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.BrandListEntity;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.CategoryListEntity;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.ChildrenEntity;
import com.cskaoyan.mapper.MarketGoodsAttributeMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.cskaoyan.mapper.MarketGoodsProductMapper;
import com.cskaoyan.mapper.MarketGoodsSpecificationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @description: 系统管理-商品管理
 * @author: Sssd
 * @date: 2022年06月26日 13:47
 */
@Service
public class AdminGoodsServiceImpl implements AdminGoodsService{

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Autowired
    MarketGoodsAttributeMapper marketGoodsAttributeMapper;

    @Autowired
    MarketGoodsProductMapper marketGoodsProductMapper;

    @Autowired
    MarketGoodsSpecificationMapper marketGoodsSpecificationMapper;

    @Override
    public CommonData<MarketGoods> qurryAllGoods(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId) {

        MarketGoodsExample example = new MarketGoodsExample();
        String orderByClause = baseParam.getSort() + " " + baseParam.getOrder();

        // 查询 deleted = 0 （false） 的所有商品
        MarketGoodsExample.Criteria or = example.or();
        or.andDeletedEqualTo(false);

        // 商品名称模糊查询
        if (name != null) {
            String sqlName = "%" + name + "%";
            or.andNameLike(sqlName);
        }

        // 商品编号查询
        if (goodsSn != null) {
            String sqlGoodsSn = "%" + goodsSn + "%";
            or.andGoodsSnLike(sqlGoodsSn);
        }

        // 商品 id 查询
        if (goodsId != null) {
            or.andIdEqualTo(goodsId);
        }

        // 设置排序语句
        example.setOrderByClause(orderByClause);

        // 配置分页工具, 注意写前面
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(example);

        // 分页工具
        PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
        return CommonData.data(marketGoodsPageInfo);

    }

    @Override
    public AdminGoodsCatAndBrandVo qurryAllCatAndBrand() {
        List<CategoryListEntity> categoryListEntityList = marketGoodsMapper.selectAllDadCat();

        for (CategoryListEntity categoryListEntity : categoryListEntityList) {
            List<ChildrenEntity> childrenEntityList = marketGoodsMapper.selectAddSonCat(categoryListEntity.getValue());
            categoryListEntity.setChildren(childrenEntityList);
        }

        List<BrandListEntity> brandListEntityList = marketGoodsMapper.selectAllBrand();

        AdminGoodsCatAndBrandVo adminGoodsCatAndBrandVo = new AdminGoodsCatAndBrandVo();
        adminGoodsCatAndBrandVo.setBrandList(brandListEntityList);
        adminGoodsCatAndBrandVo.setCategoryList(categoryListEntityList);

        return adminGoodsCatAndBrandVo;
    }

    @Override
    public void addGoods(AdminGoodsCreateBo bo) {
        // 新建一个时间用于赋 date 值
        Date date = new Date();

        AdminGoodsCreateGoodsBo goods = bo.getGoods();
        MarketGoods marketGoods = new MarketGoods();
        marketGoods.setUpdateTime(date);
        marketGoods.setAddTime(date);
        marketGoods.setDeleted(false);
        marketGoods.setBrief(goods.getBrief());
        marketGoods.setBrandId(goods.getBrandId());
        marketGoods.setCategoryId(goods.getCategoryId());
        marketGoods.setCounterPrice(goods.getCounterPrice());
        marketGoods.setGoodsSn(goods.getGoodsSn());
        marketGoods.setDetail(goods.getDetail());
        marketGoods.setGallery(goods.getGallery());
        marketGoods.setIsHot(goods.getIsHot());
        marketGoods.setIsNew(goods.getIsNew());
        marketGoods.setIsOnSale(goods.getIsOnSale());
        marketGoods.setKeywords(goods.getKeywords());
        marketGoods.setName(goods.getName());
        marketGoods.setPicUrl(goods.getPicUrl());
        marketGoods.setShareUrl(goods.getShareUrl());
        marketGoods.setUnit(goods.getUnit());
        marketGoods.setRetailPrice(goods.getRetailPrice());
        marketGoodsMapper.insertSelective(marketGoods);

        // 插入后获取主键 id
        Integer goodsId = marketGoods.getId();

        // 循环插入 attribute
        List<AdminGoodsCreateAttributeBo> attributes = bo.getAttributes();
        for (AdminGoodsCreateAttributeBo attribute : attributes) {
            MarketGoodsAttribute marketGoodsAttribute = new MarketGoodsAttribute();
            marketGoodsAttribute.setGoodsId(goodsId);
            marketGoodsAttribute.setAddTime(date);
            marketGoodsAttribute.setUpdateTime(date);
            marketGoodsAttribute.setDeleted(false);
            marketGoodsAttribute.setValue(attribute.getValue());
            marketGoodsAttribute.setAttribute(attribute.getAttribute());

            marketGoodsAttributeMapper.insertSelective(marketGoodsAttribute);
        }

        // 循环插入 specification
        List<AdminGoodsCreateSpecificationBo> specifications = bo.getSpecifications();
        for (AdminGoodsCreateSpecificationBo specification : specifications) {
            MarketGoodsSpecification marketGoodsSpecification = new MarketGoodsSpecification();
            marketGoodsSpecification.setAddTime(date);
            marketGoodsSpecification.setUpdateTime(date);
            marketGoodsSpecification.setSpecification(specification.getSpecification());
            marketGoodsSpecification.setGoodsId(goodsId);
            marketGoodsSpecification.setDeleted(false);
            marketGoodsSpecification.setPicUrl(specification.getPicUrl());
            marketGoodsSpecification.setValue(specification.getValue());
            marketGoodsSpecificationMapper.insertSelective(marketGoodsSpecification);
        }

        // 循环插入 product
        List<AdminGoodsCreateProductBo> products = bo.getProducts();
        for (AdminGoodsCreateProductBo product : products) {
            MarketGoodsProduct marketGoodsProduct = new MarketGoodsProduct();
            marketGoodsProduct.setGoodsId(goodsId);
            marketGoodsProduct.setAddTime(date);
            marketGoodsProduct.setDeleted(false);
            marketGoodsProduct.setUpdateTime(date);
            marketGoodsProduct.setUrl(product.getUrl());
            marketGoodsProduct.setNumber(Integer.parseInt(product.getNumber()));
            marketGoodsProduct.setPrice(NumberUtils.createBigDecimal(product.getPrice()));
            marketGoodsProduct.setSpecifications(product.getSpecifications());
            marketGoodsProductMapper.insertSelective(marketGoodsProduct);
        }

    }
}
