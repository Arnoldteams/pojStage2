package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxGoodsCategory.WxGoodsCategoryVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailCommentVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailVo;
import com.cskaoyan.bean.vo.wxGoodsList.WxGoodsListVo;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Integer countGoods() {
        long l = marketGoodsMapper.countByExample(null);
        return (int) l;
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
    public WxGoodsListVo quarryList(BaseParam baseParam, Integer id) {
        WxGoodsListVo wxGoodsListVo = new WxGoodsListVo();

//        if (baseParam.getOrder() == null) {
//            baseParam.setOrder("");
//        }
//
//        if (baseParam.getSort() == null) {
//            baseParam.setSort("");
//        }
        // 搜索 FilterCategoryList
        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        MarketCategory marketCategory = marketCategoryMapper.selectByPrimaryKey(id);
        MarketCategoryExample.Criteria criteria = marketCategoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andPidEqualTo(marketCategory.getPid());
        List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
        wxGoodsListVo.setFilterCategoryList(marketCategories);

        // 搜索 list ，商品的
        MarketGoodsExample goodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria1 = goodsExample.createCriteria();
        criteria1.andCategoryIdEqualTo(id);

        // 配置分页工具, 注意写前面
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
        if (marketGoods.size() > 0) {
            PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
            wxGoodsListVo.setList(marketGoodsPageInfo.getList());

            wxGoodsListVo.setLimit(baseParam.getLimit());
            if (baseParam.getPage() == null) {
                wxGoodsListVo.setPage(0);
            }
            wxGoodsListVo.setPage(baseParam.getPage());
            wxGoodsListVo.setPages(marketGoodsPageInfo.getPages());
            wxGoodsListVo.setTotal((int) marketGoodsPageInfo.getTotal());
            return wxGoodsListVo;
        }
        wxGoodsListVo.setPage(baseParam.getPage());
        wxGoodsListVo.setPages(0);
        wxGoodsListVo.setTotal(0);
        wxGoodsListVo.setList(marketGoods);
        return wxGoodsListVo;
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
        int count = (int) marketCommentMapper.countByExample(commentExample);
        commentExampleCriteria.andValueIdEqualTo(id);
        List<MarketComment> marketComments = marketCommentMapper.selectByExample(commentExample);
        WxGoodsDetailCommentVo wxGoodsDetailCommentVo = new WxGoodsDetailCommentVo();
        wxGoodsDetailCommentVo.setCount(count);
        wxGoodsDetailCommentVo.setData(marketComments);
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
        MarketGoodsSpecificationExample goodsSpecificationExample = new MarketGoodsSpecificationExample();
        MarketGoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = goodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andDeletedEqualTo(false);
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(id);
        List<MarketGoodsSpecification> marketGoodsSpecifications = marketGoodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        wxGoodsDetailVo.setSpecificationList(marketGoodsSpecifications);

        // 11. 搜索
        MarketCollectExample collectExample = new MarketCollectExample();
        MarketCollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
        collectExampleCriteria.andDeletedEqualTo(false);
        collectExampleCriteria.andTypeEqualTo((byte) 0);
        int count1 = (int) marketCollectMapper.countByExample(collectExample);
        wxGoodsDetailVo.setUserHasCollect(count1);

        return wxGoodsDetailVo;
    }
}
