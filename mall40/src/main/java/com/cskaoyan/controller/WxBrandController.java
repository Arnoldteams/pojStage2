package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminBrandVO;
import com.cskaoyan.service.WxBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author sprinkle
 * @since 2022/06/29 11:29
 */
@RestController
@RequestMapping("wx/brand")
public class WxBrandController {

    @Autowired
    WxBrandService wxBrandService;

    @GetMapping("list")
    public BaseRespVo<CommonData> login(Integer page,Integer limit){

        CommonData<MarketBrand> adminBrandVO = wxBrandService.quaryAllBrand(page,limit);

        return BaseRespVo.ok(adminBrandVO);
    }

    @GetMapping("detail")
    public BaseRespVo<MarketBrand> login(String id){

        MarketBrand marketBrand= wxBrandService.queryOneBrand(id);

        return BaseRespVo.ok(marketBrand);
    }

}
