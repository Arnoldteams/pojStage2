package com.cskaoyan.service;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxGoodsCategory.WxGoodsCategoryVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailVo;
import com.cskaoyan.bean.vo.wxGoodsList.WxGoodsListVo;

public interface WxGoodsService {
    /**
     * @author: Sssd
     * @description: 获取商品数量
     */
    Integer countGoods();

    /**
     * @author: Sssd
     * @description: 点击商品品类 - 展示商品兄弟品类，当前品类，父品类
     */
    WxGoodsCategoryVo quarryCategory(Integer id);

    /**
     * @author: Sssd
     * @description: 显示商品 List
     */
    WxGoodsListVo quarryList(BaseParam baseParam, Integer categoryId, Integer brandId, String keyword);

    WxGoodsDetailVo quarryGoodsDetail(Integer id);

    CommonData<MarketGoods> quarryRelatedGoods(Integer id);
}
