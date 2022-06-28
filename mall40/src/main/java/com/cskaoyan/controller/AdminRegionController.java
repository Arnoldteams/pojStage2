package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketRegion;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-27 20:13:20
 * @version:
 * @Description: 商城管理-行政区域
 */

@RestController
@RequestMapping("admin/region")
public class AdminRegionController {

    @Autowired
    AdminRegionService adminRegionService;


    /**
     * 展示全部行政区域信息
     *
     * @return com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.param.CommonData><com.cskaoyan.bean.vo.MarketRegionInfoVO> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/28 10:26
     */

    @RequestMapping("list")
    public BaseRespVo getRegionList(){
        CommonData data = adminRegionService.getRegionList();
        return BaseRespVo.ok(data);
    }


}
