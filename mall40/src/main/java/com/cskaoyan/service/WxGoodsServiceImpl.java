package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxGoodsCategory.WxGoodsCategoryVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailCommentVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailSpecificationVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailVo;
import com.cskaoyan.bean.vo.wxGoodsList.WxGoodsListVo;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 14:49
 */
@Service
@Transactional
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Autowired
    MarketGoodsAttributeMapper marketGoodsAttributeMapper;

    @Autowired
    MarketBrandMapper marketBrandMapper;

    @Autowired
    MarketCommentMapper marketCommentMapper;

    @Autowired
    MarketGrouponRulesMapper marketGrouponRulesMapper;

    @Autowired
    MarketIssueMapper marketIssueMapper;

    @Autowired
    MarketGoodsProductMapper marketGoodsProductMapper;

    @Autowired
    MarketGoodsSpecificationMapper marketGoodsSpecificationMapper;

    @Autowired
    MarketCollectMapper marketCollectMapper;

    @Autowired
    HttpSession session;

    @Override
    public Integer countGoods() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        if (principals == null) {
            return null;
        }
        MarketUser principals1 = (MarketUser) principals;
        Integer userId = principals1.getId();
        MarketCollectExample collectExample = new MarketCollectExample();
        MarketCollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
        collectExampleCriteria.andDeletedEqualTo(false);
        collectExampleCriteria.andTypeEqualTo((byte) 0);
        collectExampleCriteria.andValueIdEqualTo(userId);
        int count = (int) marketCollectMapper.countByExample(collectExample);
        return count;
    }

    @Override
    public WxGoodsCategoryVo quarryCategory(Integer id) {
        WxGoodsCategoryVo wxGoodsCategoryVo = new WxGoodsCategoryVo();

        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria = marketCategoryExample.createCriteria();


        // 判断当前品类是否为 父品类
        MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(id);
        // 当前品类为父品类
        if (marketCategory.getPid() == 0) {
            wxGoodsCategoryVo.setParentCategory(marketCategory);
            // 搜索子品类列表
            criteria.andPidEqualTo(id);
            criteria.andDeletedEqualTo(false);
            List<MarketCategory> brotherCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
            wxGoodsCategoryVo.setBrotherCategory(brotherCategories);

            // 将子平类第一个设为当前品类
            wxGoodsCategoryVo.setCurrentCategory(brotherCategories.get(0));
            return wxGoodsCategoryVo;
        }
        // 不是 父品类的情况
        // 设置当前品类
        wxGoodsCategoryVo.setCurrentCategory(marketCategory);

        // 搜索兄弟品类
        criteria.andPidEqualTo(marketCategory.getPid());
        List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
        wxGoodsCategoryVo.setBrotherCategory(marketCategories);

        // 搜索父品类
        MarketCategory marketCategory1 = marketCategoryMapper.selectByPrimaryKey(marketCategory.getPid());
        wxGoodsCategoryVo.setParentCategory(marketCategory1);

        return wxGoodsCategoryVo;

    }

    @Override
    public WxGoodsDetailVo quarryGoodsDetail(Integer id) {
        WxGoodsDetailVo wxGoodsDetailVo = new WxGoodsDetailVo();

        // 1. 搜索 info
        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(id);
        wxGoodsDetailVo.setInfo(marketGoods);

        // 2. 搜索 attribute 列表
        MarketGoodsAttributeExample attributeExample = new MarketGoodsAttributeExample();
        MarketGoodsAttributeExample.Criteria attributeExampleCriteria = attributeExample.createCriteria();
        attributeExampleCriteria.andGoodsIdEqualTo(id);
        attributeExampleCriteria.andDeletedEqualTo(false);
        List<MarketGoodsAttribute> attributes = marketGoodsAttributeMapper.selectByExample(attributeExample);
        wxGoodsDetailVo.setAttribute(attributes);

        // 3. 搜索 brand
        MarketBrand marketBrand = marketBrandMapper.selectByPrimaryKey(marketGoods.getBrandId());
        wxGoodsDetailVo.setBrand(marketBrand);

        // 4. 搜索 comment
        MarketCommentExample commentExample = new MarketCommentExample();
        MarketCommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andTypeEqualTo((byte) 0);
        commentExampleCriteria.andDeletedEqualTo(false);
        commentExampleCriteria.andValueIdEqualTo(id);
        List<MarketComment> marketComments = marketCommentMapper.selectByExample(commentExample);
        WxGoodsDetailCommentVo wxGoodsDetailCommentVo = new WxGoodsDetailCommentVo();
        wxGoodsDetailCommentVo.setCount(marketComments.size());
        if (marketComments.size() > 2) {
            wxGoodsDetailCommentVo.setData(marketComments.subList(0, 2));
        } else {
            wxGoodsDetailCommentVo.setData(marketComments);
        }
        wxGoodsDetailVo.setComment(wxGoodsDetailCommentVo);

        // 5. 搜索 groupon
        MarketGrouponRulesExample grouponRulesExample = new MarketGrouponRulesExample();
        MarketGrouponRulesExample.Criteria grouponRulesExampleCriteria = grouponRulesExample.createCriteria();
        grouponRulesExampleCriteria.andDeletedEqualTo(false);
        grouponRulesExampleCriteria.andGoodsIdEqualTo(id);
        List<MarketGrouponRules> marketGrouponRules = marketGrouponRulesMapper.selectByExample(grouponRulesExample);
        wxGoodsDetailVo.setGroupon(marketGrouponRules);

        // 6. 搜索 issue
        List<MarketIssue> marketIssues = marketIssueMapper.selectByExample(null);
        wxGoodsDetailVo.setIssue(marketIssues);

        // 7. 搜索 products
        MarketGoodsProductExample goodsProductExample = new MarketGoodsProductExample();
        MarketGoodsProductExample.Criteria goodsProductExampleCriteria = goodsProductExample.createCriteria();
        goodsProductExampleCriteria.andDeletedEqualTo(false);
        goodsProductExampleCriteria.andGoodsIdEqualTo(id);
        List<MarketGoodsProduct> marketGoodsProducts = marketGoodsProductMapper.selectByExample(goodsProductExample);
        wxGoodsDetailVo.setProductList(marketGoodsProducts);

        // 8.9.  搜索 share
        if (marketGoods.getShareUrl() != null) {
            wxGoodsDetailVo.setShare(true);
            wxGoodsDetailVo.setShareImage(marketGoods.getShareUrl());
        } else {
            wxGoodsDetailVo.setShare(false);
        }

        // 10. 搜索 specificationList
        MarketGoodsSpecificationExample marketGoodsSpecificationExample = new MarketGoodsSpecificationExample();
        MarketGoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = marketGoodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andDeletedEqualTo(false);
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(id);
        List<MarketGoodsSpecification> marketGoodsSpecifications = marketGoodsSpecificationMapper.selectByExample(marketGoodsSpecificationExample);

        // 利用 hashSet 存储名字用于分组
        HashSet<String> set = new HashSet<>();
        for (MarketGoodsSpecification marketGoodsSpecification : marketGoodsSpecifications) {
            set.add(marketGoodsSpecification.getSpecification());
        }

        ArrayList<WxGoodsDetailSpecificationVo> wxGoodsDetailSpecificationVos = new ArrayList<>();

        // 根据名字再次查找
        for (String s : set) {
            WxGoodsDetailSpecificationVo wxGoodsDetailSpecificationVo = new WxGoodsDetailSpecificationVo();
            wxGoodsDetailSpecificationVo.setName(s);

            goodsSpecificationExampleCriteria.andSpecificationEqualTo(s);
            List<MarketGoodsSpecification> marketGoodsSpecifications1 = marketGoodsSpecificationMapper.selectByExample(marketGoodsSpecificationExample);
            wxGoodsDetailSpecificationVo.setValueList(marketGoodsSpecifications1);
            wxGoodsDetailSpecificationVos.add(wxGoodsDetailSpecificationVo);
        }
        wxGoodsDetailVo.setSpecificationList(wxGoodsDetailSpecificationVos);


        // 11. 搜索 hasCollect
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        if (principals == null) {
            wxGoodsDetailVo.setUserHasCollect(0);
            return wxGoodsDetailVo;
        }
        MarketUser marketUser = (MarketUser) principals.getPrimaryPrincipal();
        MarketCollectExample collectExample = new MarketCollectExample();
        MarketCollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
        collectExampleCriteria.andDeletedEqualTo(false);
        collectExampleCriteria.andUserIdEqualTo(marketUser.getId());
        collectExampleCriteria.andTypeEqualTo((byte) 0);
        collectExampleCriteria.andValueIdEqualTo(id);
        List<MarketCollect> marketCollects = marketCollectMapper.selectByExample(collectExample);
        if (marketCollects.size() == 0) {
            wxGoodsDetailVo.setUserHasCollect(0);
        } else {
            wxGoodsDetailVo.setUserHasCollect(1);
        }

        return wxGoodsDetailVo;
    }

    @Override
    public CommonData<MarketGoods> quarryRelatedGoods(Integer id) {

        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(id);

        MarketGoodsExample goodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andCategoryIdEqualTo(marketGoods.getCategoryId());
        criteria.andIdNotEqualTo(id);

        PageHelper.startPage(1, 6);
        List<MarketGoods> goods = marketGoodsMapper.selectByExample(goodsExample);

        PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(goods);

        return CommonData.data(marketGoodsPageInfo);
    }

    @Override
    public WxGoodsListVo quarryList(Boolean isHot, Boolean isNew, Integer categoryId, Integer brandId, String keyword, BaseParam baseParam) {

        WxGoodsListVo wxGoodsListVo = new WxGoodsListVo();
        // 1. 仅有 categoryId
        if (categoryId != null && keyword == null && brandId == null && isHot == null && isNew == null) {
            // 搜索 FilterCategoryListy
            MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
            MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(categoryId);
            MarketCategoryExample.Criteria criteria = marketCategoryExample.createCriteria();
            criteria.andDeletedEqualTo(false);
            criteria.andPidEqualTo(marketCategory.getPid());
            List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
            wxGoodsListVo.setFilterCategoryList(marketCategories);

            // 搜索 list ，商品的
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            MarketGoodsExample.Criteria criteria1 = goodsExample.createCriteria();
            criteria1.andCategoryIdEqualTo(categoryId);

            // 配置分页工具, 注意写前面
            PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
            List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
            PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
            wxGoodsListVo.setLimit(baseParam.getLimit());
            wxGoodsListVo.setPage(baseParam.getPage());
            wxGoodsListVo.setList(marketGoodsPageInfo.getList());
            wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
            wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
            return wxGoodsListVo;
        }
        // 2. 仅有 brandId 的情况
        if (brandId != null && keyword == null && categoryId == null && isHot == null && isNew == null) {
            // 搜索 list
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            MarketGoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
            goodsExampleCriteria.andDeletedEqualTo(false);
            goodsExampleCriteria.andBrandIdEqualTo(brandId);
            // 分页
            PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
            List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
            PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
            // 搜索 filter


            // 设置返回值
            wxGoodsListVo.setList(marketGoodsPageInfo.getList());
            wxGoodsListVo.setLimit(baseParam.getLimit());
            wxGoodsListVo.setPage(baseParam.getPage());
            wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
            wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
            return wxGoodsListVo;
        }
        // 3. 有 keyword 和 category
        if (brandId == null && keyword != null && categoryId != null && isHot == null && isNew == null) {
            // 搜索 list
            session.setAttribute("keyword", keyword);
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            goodsExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
            MarketGoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
            goodsExampleCriteria.andDeletedEqualTo(false);
            goodsExampleCriteria.andNameLike("%" + keyword + "%");
            if (categoryId == 0) {
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);
                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                // 搜索 filterList
                List<MarketCategory> categoryArrayList = new ArrayList<>();
                HashSet<MarketCategory> categoryHashSet = new HashSet<>();
                for (MarketGoods marketGood : marketGoods1) {
                    Integer categoryId1 = marketGood.getCategoryId();
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(categoryId1);
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);

                // 设置值
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
                return wxGoodsListVo;
            }
            if (categoryId != 0) {
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);
                List<MarketCategory> categoryArrayList = new ArrayList<>();

                goodsExampleCriteria.andCategoryIdEqualTo(categoryId);
                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
                // 搜索 filterList

                HashSet<MarketCategory> categoryHashSet = new HashSet<>();
                for (MarketGoods marketGood : marketGoods1) {
                    Integer categoryId1 = marketGood.getCategoryId();
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(categoryId1);
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                // 设置值
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
                return wxGoodsListVo;
            }
            return wxGoodsListVo;
        }
        // 4. 有 isNew
        if (brandId == null && keyword == null && categoryId != null && isHot == null && isNew != null) {
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            goodsExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
            MarketGoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
            if (categoryId == 0) {
                goodsExampleCriteria.andIsNewEqualTo(isNew);
                goodsExampleCriteria.andDeletedEqualTo(false);
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);
                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                List<MarketCategory> categoryArrayList = new ArrayList<>();
                HashSet<MarketCategory> categoryHashSet = new HashSet<>();

                for (MarketGoods marketGood : marketGoods1) {
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(marketGood.getCategoryId());
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);

                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
            }

            if (categoryId != 0) {
                goodsExampleCriteria.andIsNewEqualTo(isNew);
                goodsExampleCriteria.andDeletedEqualTo(false);
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);

                goodsExampleCriteria.andCategoryIdEqualTo(categoryId);

                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
                List<MarketCategory> categoryArrayList = new ArrayList<>();
                HashSet<MarketCategory> categoryHashSet = new HashSet<>();

                for (MarketGoods marketGood : marketGoods1) {
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(marketGood.getCategoryId());
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
            }


            return wxGoodsListVo;
        }
        // 5. 有 isHot
        if (brandId == null && keyword == null && categoryId != null && isHot != null && isNew == null) {
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            goodsExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
            MarketGoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
            goodsExampleCriteria.andIsHotEqualTo(isHot);
            goodsExampleCriteria.andDeletedEqualTo(false);
            if (categoryId == 0) {
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);
                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                List<MarketCategory> categoryArrayList = new ArrayList<>();
                HashSet<MarketCategory> categoryHashSet = new HashSet<>();

                for (MarketGoods marketGood : marketGoods1) {
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(marketGood.getCategoryId());
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);

                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
                return wxGoodsListVo;
            }

            if (categoryId != 0) {
                List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample);
                List<MarketCategory> categoryArrayList = new ArrayList<>();
                HashSet<MarketCategory> categoryHashSet = new HashSet<>();
                for (MarketGoods marketGood : marketGoods1) {
                    MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(marketGood.getCategoryId());
                    categoryHashSet.add(marketCategory);
                }
                categoryArrayList.addAll(categoryHashSet);
                goodsExampleCriteria.andCategoryIdEqualTo(categoryId);
                PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);

                wxGoodsListVo.setFilterCategoryList(categoryArrayList);
                wxGoodsListVo.setList(marketGoodsPageInfo.getList());
                wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
                wxGoodsListVo.setPage(baseParam.getPage());
                wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
                wxGoodsListVo.setLimit(baseParam.getLimit());
                return wxGoodsListVo;
            }
        }
        return null;
    }
}
