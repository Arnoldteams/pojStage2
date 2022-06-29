package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.vo.wxHomeIndex.FloorGoodsVo;
import com.cskaoyan.bean.vo.wxHomeIndex.WxHomeIndexVo;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月28日 22:06
 */
@Service
public class WxHomeServiceImpl implements WxHomeService{

    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Autowired
    MarketAdMapper marketAdMapper;

    @Autowired
    MarketBrandMapper marketBrandMapper;

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Autowired
    MarketCouponMapper marketCouponMapper;

    @Autowired
    MarketTopicMapper marketTopicMapper;


    @Override
    public WxHomeIndexVo homeIndex() {
        WxHomeIndexVo wxHomeIndexVo = new WxHomeIndexVo();

        // 搜索 banner
        MarketAdExample marketAdExample = new MarketAdExample();
        MarketAdExample.Criteria criteria = marketAdExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<MarketAd> marketAds = marketAdMapper.selectByExample(marketAdExample);
        wxHomeIndexVo.setBanner(marketAds);

        // 搜索 brandList
        MarketBrandExample marketBrandExample = new MarketBrandExample();
        MarketBrandExample.Criteria criteria1 = marketBrandExample.createCriteria();
        criteria1.andDeletedEqualTo(false);
        List<MarketBrand> marketBrands = marketBrandMapper.selectByExample(marketBrandExample);
        wxHomeIndexVo.setBrandList(marketBrands);

        // 搜索 channelList
        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria2 = marketCategoryExample.createCriteria();
        criteria2.andPidEqualTo(0);
        criteria2.andDeletedEqualTo(false);
        List<MarketCategory> marketCategories = marketCategoryMapper.selectByExample(marketCategoryExample);
        wxHomeIndexVo.setChannel(marketCategories);

        // 搜索 marketCoupon List
        MarketCouponExample marketCouponExample = new MarketCouponExample();
        MarketCouponExample.Criteria criteria3 = marketCouponExample.createCriteria();
        criteria3.andDeletedEqualTo(false);
        List<MarketCoupon> couponList = marketCouponMapper.selectByExample(marketCouponExample);
        wxHomeIndexVo.setCouponList(couponList);

        // 搜索 floorGoods List
        List<FloorGoodsVo> floorGoodsVos = new ArrayList<>();
        for (MarketCategory marketCategory : marketCategories) {
            FloorGoodsVo floorGoodsVo = new FloorGoodsVo();
            floorGoodsVo.setId(marketCategory.getId());
            floorGoodsVo.setName(marketCategory.getName());
            MarketGoodsExample goodsExample = new MarketGoodsExample();
            MarketGoodsExample.Criteria criteria4 = goodsExample.createCriteria();
            criteria4.andCategoryIdEqualTo(marketCategory.getId());
            criteria4.andDeletedEqualTo(false);
            List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
            floorGoodsVo.setGoodsList(marketGoods);
            floorGoodsVos.add(floorGoodsVo);
        }
        wxHomeIndexVo.setFloorGoodsList(floorGoodsVos);

        // 搜索 hotGoods List
        MarketGoodsExample goodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria4 = goodsExample.createCriteria();
        criteria4.andDeletedEqualTo(false);
        criteria4.andIsHotEqualTo(true);
        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
        wxHomeIndexVo.setHotGoodsList(marketGoods);

        // 搜索 newGoods List
        MarketGoodsExample goodsExample1 = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria5 = goodsExample1.createCriteria();
        criteria5.andDeletedEqualTo(false);
        criteria5.andIsNewEqualTo(true);
        List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample1);
        wxHomeIndexVo.setNewGoodsList(marketGoods1);

        // 搜索 topicList
        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria criteria6 = marketTopicExample.createCriteria();
        criteria6.andDeletedEqualTo(false);
        List<MarketTopic> marketTopics = marketTopicMapper.selectByExample(marketTopicExample);
        wxHomeIndexVo.setTopicList(marketTopics);

        return wxHomeIndexVo;
    }
}
