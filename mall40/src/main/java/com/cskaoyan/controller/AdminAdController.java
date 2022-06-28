package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketAd;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 夏一男
 * @createTime: 2022/6/25 23:17
 * @version:
 * @Description:广告管理
 */
@RestController
@RequestMapping("admin/ad")
public class AdminAdController {

    @Autowired
    AdminAdService adService;

    /**
     * 展示全部广告
     *
     * @param info 分页信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/26 11:23
     */
    @RequestMapping("list")
    public BaseRespVo getAdList(String name, String content, BasePageInfo info){
        CommonData data = adService.getAdList(name,content,info);
        return BaseRespVo.ok(data);
    }



    /**
     * 修改某个广告信息
     *
     * @param marketAd 广告信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketAd> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/26 11:29
     */

    @RequestMapping("update")
    public BaseRespVo changeAdById(@RequestBody MarketAd marketAd){
        MarketAd updatedMarketAd = adService.changeAd(marketAd);
        return BaseRespVo.ok(updatedMarketAd);
    }

    /**
     * 添加一条广告
     *
     * @param marketAd 广告信息
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketAd> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/26 14:39
     */

    @RequestMapping("create")
    public BaseRespVo addAd(@RequestBody MarketAd marketAd){
        MarketAd insertedMarkAd = adService.addAd(marketAd);
        return BaseRespVo.ok(insertedMarkAd);
    }


    /**
     * 删除指定广告
     *
     * @param marketAd 广告信息
     * @return com.cskaoyan.bean.BaseRespVo 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/26 15:04
     */

    @RequestMapping("delete")
    public BaseRespVo deleteAd(@RequestBody MarketAd marketAd){
        Boolean aBoolean = adService.deleteAdById(marketAd);
        if(aBoolean){
            return BaseRespVo.ok(null);
        }
        BaseRespVo<String> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }
}
