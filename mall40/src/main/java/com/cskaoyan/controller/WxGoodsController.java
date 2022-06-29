package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxGoodsCategory.WxGoodsCategoryVo;
import com.cskaoyan.bean.vo.wxGoodsDetailVo.WxGoodsDetailVo;
import com.cskaoyan.bean.vo.wxGoodsList.WxGoodsListVo;
import com.cskaoyan.service.WxGoodsService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 14:48
 */
@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    WxGoodsService wxGoodsService;

    /**
     * @author: Sssd
     * @description: 获取去商品数量
     */
    @RequestMapping("count")
    public BaseRespVo wxGoodsCount() {
        Integer num = wxGoodsService.countGoods();
        return BaseRespVo.ok(num);
    }

    /**
     * @author: Sssd
     * @description: 获取商品品类
     */
    @RequestMapping("category")
    public BaseRespVo wxGoodsCategory(Integer id) {
        WxGoodsCategoryVo vo = wxGoodsService.quarryCategory(id);
        return BaseRespVo.ok(vo);
    }

    @RequestMapping("list")
    public BaseRespVo wxGoodsList(Integer categoryId,BaseParam baseParam) {
        WxGoodsListVo vo = wxGoodsService.quarryList(baseParam, categoryId);
        return BaseRespVo.ok(vo);
    }

    @RequestMapping("detail")
    public BaseRespVo wxGoodsDetail(Integer id) {
        WxGoodsDetailVo vo = wxGoodsService.quarryGoodsDetail(id);
        return BaseRespVo.ok(vo);
    }

    @RequestMapping("related")
    public BaseRespVo wxGoodsRelated(Integer id) {
        CommonData<MarketGoods> data = wxGoodsService.quarryRelatedGoods(id);
        return BaseRespVo.ok(data);
    }

}
