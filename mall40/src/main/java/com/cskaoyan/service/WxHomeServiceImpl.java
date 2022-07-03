package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.vo.WxHomeAboutVO;
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
public class WxHomeServiceImpl implements WxHomeService {

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

    @Autowired
    MarketSystemMapper marketSystemMapper;

    @Override
    public WxHomeIndexVo homeIndex() {

        List<MarketSystem> system = marketSystemMapper.selectByExample(null);

        Integer topicNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(18).getKeyValue());
        Integer newNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(2).getKeyValue());
        Integer hotNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(10).getKeyValue());
        Integer brandNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(17).getKeyValue());
        Integer catNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(15).getKeyValue());
        Integer catGoodsNum = Integer.valueOf(marketSystemMapper.selectByPrimaryKey(18).getKeyValue());

        // 获取配置值
        for (MarketSystem s : system) {
            if (s.getKeyName().equals("market_wx_index_topic")) {
                topicNum = Integer.parseInt(s.getKeyValue());
            }
            if (s.getKeyName().equals("market_wx_index_new")) {
                newNum = Integer.parseInt(s.getKeyValue());
            }
            if (s.getKeyName().equals("market_wx_index_hot")) {
                hotNum = Integer.parseInt(s.getKeyValue());
            }
            if (s.getKeyName().equals("market_wx_index_brand")) {
                brandNum = Integer.parseInt(s.getKeyValue());
            }
            if (s.getKeyName().equals("market_wx_index_topic")) {
                catNum = Integer.parseInt(s.getKeyValue());
            }
            if (s.getKeyName().equals("market_wx_catlog_goods")) {
                catGoodsNum = Integer.parseInt(s.getKeyValue());
            }

        }

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
        if (marketBrands.size() > brandNum) {
            wxHomeIndexVo.setBrandList(marketBrands.subList(0, brandNum));
        } else {
            wxHomeIndexVo.setBrandList(marketBrands);
        }


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
        if (couponList.size() > 3) {
            wxHomeIndexVo.setCouponList(couponList.subList(0, 3));
        } else {
            wxHomeIndexVo.setCouponList(couponList);
        }


        // 搜索 floorGoods List
        List<FloorGoodsVo> floorGoodsVos = new ArrayList<>();
        for (MarketCategory marketCategory : marketCategories) {
            FloorGoodsVo floorGoodsVo = new FloorGoodsVo();
            MarketCategoryExample categoryExample = new MarketCategoryExample();
            MarketCategoryExample.Criteria categoryExampleCriteria = categoryExample.createCriteria();
            categoryExampleCriteria.andDeletedEqualTo(false);
            categoryExampleCriteria.andPidEqualTo(marketCategory.getId());
            List<MarketCategory> marketCategories1 = marketCategoryMapper.selectByExample(categoryExample);
            List<MarketGoods> goodsArrayList = new ArrayList<>();
            for (MarketCategory category : marketCategories1) {
                MarketGoodsExample goodsExample = new MarketGoodsExample();
                MarketGoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
                goodsExampleCriteria.andDeletedEqualTo(false);
                goodsExampleCriteria.andCategoryIdEqualTo(category.getId());
                List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
                goodsArrayList.addAll(marketGoods);
            }
            if (goodsArrayList.size() > catGoodsNum) {
                floorGoodsVo.setGoodsList(goodsArrayList.subList(0, catGoodsNum));
            } else {
                floorGoodsVo.setGoodsList(goodsArrayList);
            }
            floorGoodsVo.setId(marketCategory.getId());
            floorGoodsVo.setName(marketCategory.getName());
            floorGoodsVos.add(floorGoodsVo);
        }

//        if (floorGoodsVos.size() > 12) {
//            wxHomeIndexVo.setFloorGoodsList(floorGoodsVos.subList(0, 12));
//        } else {
//
//        }
        if (floorGoodsVos.size() > catNum) {
            wxHomeIndexVo.setFloorGoodsList(floorGoodsVos.subList(0, catNum));
        } else {
            wxHomeIndexVo.setFloorGoodsList(floorGoodsVos);
        }


        // 搜索 hotGoods List
        MarketGoodsExample goodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria4 = goodsExample.createCriteria();
        criteria4.andDeletedEqualTo(false);
        criteria4.andIsHotEqualTo(true);
        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(goodsExample);
        if (marketGoods.size() > hotNum) {
            wxHomeIndexVo.setHotGoodsList(marketGoods.subList(0, hotNum));
        } else {
            wxHomeIndexVo.setHotGoodsList(marketGoods);
        }


        // 搜索 newGoods List
        MarketGoodsExample goodsExample1 = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria5 = goodsExample1.createCriteria();
        criteria5.andDeletedEqualTo(false);
        criteria5.andIsNewEqualTo(true);
        List<MarketGoods> marketGoods1 = marketGoodsMapper.selectByExample(goodsExample1);
        if (marketGoods1.size() > newNum) {
            wxHomeIndexVo.setNewGoodsList(marketGoods1.subList(0, newNum));
        } else {
            wxHomeIndexVo.setNewGoodsList(marketGoods1);
        }


        // 搜索 topicList
        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria criteria6 = marketTopicExample.createCriteria();
        criteria6.andDeletedEqualTo(false);
        List<MarketTopic> marketTopics = marketTopicMapper.selectByExample(marketTopicExample);
        if (marketTopics.size() > topicNum) {
            wxHomeIndexVo.setTopicList(marketTopics.subList(0, topicNum));
        } else {
            wxHomeIndexVo.setTopicList(marketTopics);
        }
        wxHomeIndexVo.setTopicList(marketTopics);

        return wxHomeIndexVo;
    }

    /**
     * @description:
     * @author: 帅的批爆 的 sprinkle
     * @date: 2022年06月29日 23:20
     */
    @Override
    public WxHomeAboutVO getAbout() {
        MarketSystemExample example = new MarketSystemExample();
        MarketSystemExample.Criteria criteria = example.createCriteria();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("market_mall_longitude");
        strings.add("market_mall_latitude");
        strings.add("market_mall_address");
        strings.add("market_mall_qq");
        strings.add("market_mall_phone");
        strings.add("market_mall_name");
        criteria.andKeyNameIn(strings);

        List<MarketSystem> marketSystems = marketSystemMapper.selectByExample(example);
        WxHomeAboutVO wxHomeAboutVO = new WxHomeAboutVO();

        for (MarketSystem marketSystem : marketSystems) {
            if (marketSystem.getKeyName().equals("market_mall_longitude")) {
                wxHomeAboutVO.setLongitude(marketSystem.getKeyValue());
            }
            if (marketSystem.getKeyName().equals("market_mall_latitude")) {
                wxHomeAboutVO.setLatitude(marketSystem.getKeyValue());
            }
            if (marketSystem.getKeyName().equals("market_mall_address")) {
                wxHomeAboutVO.setAddress(marketSystem.getKeyValue());
            }
            if (marketSystem.getKeyName().equals("market_mall_qq")) {
                wxHomeAboutVO.setQq(marketSystem.getKeyValue());
            }
            if (marketSystem.getKeyName().equals("market_mall_phone")) {
                wxHomeAboutVO.setPhone(marketSystem.getKeyValue());
            }
            if (marketSystem.getKeyName().equals("market_mall_name")) {
                wxHomeAboutVO.setName(marketSystem.getKeyValue());
            }
        }

        return wxHomeAboutVO;
    }
}
