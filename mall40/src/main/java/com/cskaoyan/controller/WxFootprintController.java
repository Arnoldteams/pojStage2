package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxFootprintVO;
import com.cskaoyan.service.WxFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 13:24:54
 * @version:
 * @Description: 个人中心-浏览足迹
 */
@RestController
@RequestMapping("wx/footprint")
public class WxFootprintController {

    @Autowired
    WxFootprintService service;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 15:17:24
     * @description: 根据用户的信息，查询访问商品的足迹
     * @param: baseParam - [BaseParam]
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @GetMapping("list")
    public BaseRespVo list(BaseParam baseParam) {

        CommonData<WxFootprintVO> data = service.queryAllFootprint(baseParam);

        return BaseRespVo.ok(data);
    }

}
