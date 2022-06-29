package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.adminGoodsCreateBo.*;
import com.cskaoyan.bean.bo.adminGoodsDeleteBo.AdminGoodsDeleteBo;
import com.cskaoyan.bean.bo.adminGoodsUpdateBo.AdminGoodsUpdateBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.AdminGoodsCatAndBrandVo;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.BrandListEntity;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.CategoryListEntity;
import com.cskaoyan.bean.vo.adminGoodsCatAndBrand.ChildrenEntity;
import com.cskaoyan.bean.vo.adminGoodsDetailVo.AdminGoodsDetailVo;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @description: 系统管理-商品管理
 * @author: Sssd
 * @date: 2022年06月26日 13:47
 */
@Transactional
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

    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Autowired
    HttpSession session;

    @Autowired
    RedisTemplate redisTemplate;

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

        // goods 信息设置时间属性
        MarketGoods goods = bo.getGoods();
        goods.setUpdateTime(date);
        goods.setAddTime(date);
        // goods 插入 获取主键值
        marketGoodsMapper.insertSelective(goods);

        List<MarketGoodsProduct> products = bo.getProducts();
        // 循环插入
        for (MarketGoodsProduct product : products) {
            // products 赋值
            product.setUpdateTime(date);
            product.setAddTime(date);
            product.setGoodsId(goods.getId());
            marketGoodsProductMapper.insertSelective(product);
        }

        List<MarketGoodsAttribute> attributes = bo.getAttributes();
        for (MarketGoodsAttribute attribute : attributes) {
            attribute.setUpdateTime(date);
            attribute.setAddTime(date);
            attribute.setGoodsId(goods.getId());
            marketGoodsAttributeMapper.insertSelective(attribute);
        }

        List<MarketGoodsSpecification> specifications = bo.getSpecifications();
        for (MarketGoodsSpecification specification : specifications) {
            specification.setUpdateTime(date);
            specification.setAddTime(date);
            specification.setGoodsId(goods.getId());
            marketGoodsSpecificationMapper.insertSelective(specification);
        }

        session.setAttribute("log", goods.getId());
    }

    @Override
    public AdminGoodsDetailVo qurryGoodById(Integer id) {

        // 创建一个 Vo 类对象
        AdminGoodsDetailVo adminGoodsDetailVo = new AdminGoodsDetailVo();

        // 搜索 category 的 id 列表
        List<Integer> categoryIds = marketCategoryMapper.selectCatIds(id);

        // 搜索 goods
        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(id);

        // 搜索 attribute 列表
        // select * from market_goods_attribute where goodId = id
        MarketGoodsAttributeExample marketGoodsAttributeExample = new MarketGoodsAttributeExample();
        MarketGoodsAttributeExample.Criteria criteria = marketGoodsAttributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        List<MarketGoodsAttribute> attributes = marketGoodsAttributeMapper.selectByExample(marketGoodsAttributeExample);

        // 搜索 specifications
        MarketGoodsSpecificationExample marketGoodsSpecificationExample = new MarketGoodsSpecificationExample();
        MarketGoodsSpecificationExample.Criteria criteria1 = marketGoodsSpecificationExample.createCriteria();
        criteria1.andGoodsIdEqualTo(id);
        List<MarketGoodsSpecification> marketGoodsSpecifications = marketGoodsSpecificationMapper.selectByExample(marketGoodsSpecificationExample);

        // 搜索 products
        MarketGoodsProductExample marketGoodsProductExample = new MarketGoodsProductExample();
        MarketGoodsProductExample.Criteria criteria2 = marketGoodsProductExample.createCriteria();
        criteria2.andGoodsIdEqualTo(id);
        List<MarketGoodsProduct> marketGoodsProducts = marketGoodsProductMapper.selectByExample(marketGoodsProductExample);

        adminGoodsDetailVo.setGoods(marketGoods);
        adminGoodsDetailVo.setAttributes(attributes);
        adminGoodsDetailVo.setSpecifications(marketGoodsSpecifications);
        adminGoodsDetailVo.setProducts(marketGoodsProducts);
        adminGoodsDetailVo.setCategoryIds(categoryIds);

        return adminGoodsDetailVo;
    }

    @Override
    public void modifyGoods(AdminGoodsUpdateBo bo) {
        // 用于更新 updateTime 属性
        Date date = new Date();

        // 获取 bo 中的值
        List<MarketGoodsAttribute> attributes = bo.getAttributes();
        MarketGoods goods = bo.getGoods();
        List<MarketGoodsProduct> products = bo.getProducts();
        List<MarketGoodsSpecification> specifications = bo.getSpecifications();

        // 更新 goods 信息
        goods.setUpdateTime(date);
        marketGoodsMapper.updateByPrimaryKeySelective(goods);

        // 更新 attribute 信息
        for (MarketGoodsAttribute attribute : attributes) {
            attribute.setUpdateTime(date);
            marketGoodsAttributeMapper.updateByPrimaryKeySelective(attribute);
        }

        // 更新 specification 信息
        for (MarketGoodsSpecification specification : specifications) {
            specification.setUpdateTime(date);
            marketGoodsSpecificationMapper.updateByPrimaryKeySelective(specification);
        }

        // 更新 product 信息
        for (MarketGoodsProduct product : products) {
            product.setUpdateTime(date);
            marketGoodsProductMapper.updateByPrimaryKeySelective(product);
        }

        session.setAttribute("log", goods.getId());
    }

    @Override
    public void deleteGoods(AdminGoodsDeleteBo bo) {
        MarketGoodsExample example = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(bo.getId());
        MarketGoods marketGoods = new MarketGoods();
        marketGoods.setDeleted(true);
        marketGoodsMapper.updateByExampleSelective(marketGoods, example);

        session.setAttribute("log", bo.getId());
    }
}
