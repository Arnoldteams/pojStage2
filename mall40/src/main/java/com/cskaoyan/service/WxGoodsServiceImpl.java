package com.cskaoyan.service;

import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryExample;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxGoodsCategory.WxGoodsCategoryVo;
import com.cskaoyan.bean.vo.wxGoodsList.WxGoodsListVo;
import com.cskaoyan.mapper.MarketCategoryMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
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

        // 搜索父类品类
        MarketCategory parentCategory = marketCategoryMapper.selectByPrimaryKey(id);
        wxGoodsCategoryVo.setParentCategory(parentCategory);
        // 搜索兄弟品类列表
        criteria.andPidEqualTo(id);
        criteria.andDeletedEqualTo(false);
        List<MarketCategory> brotherCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
        wxGoodsCategoryVo.setCurrentCategory(brotherCategories.get(0));
        wxGoodsCategoryVo.setBrotherCategory(brotherCategories);

        return wxGoodsCategoryVo;
    }

    @Override
    public WxGoodsListVo quarryList(BaseParam baseParam, Integer id) {
        WxGoodsListVo wxGoodsListVo = new WxGoodsListVo();

        // 搜索 FilterCategoryList
        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria = marketCategoryExample.createCriteria();
        criteria.andPidEqualTo(id);
        List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
        wxGoodsListVo.setFilterCategoryList(marketCategories);

        // 搜索 list ，商品的
        MarketGoodsExample goodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria1 = goodsExample.createCriteria();
        criteria1.andCategoryIdEqualTo(id);

        // 配置分页工具, 注意写前面
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
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
}
