package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.WxCatalogIndexVO;
import com.cskaoyan.service.WxCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-28 22:45:47
 * @version:
 * @Description: 类目
 */
@RestController
@RequestMapping("wx/catalog")
public class WxCatalogController {

    @Autowired
    WxCatalogService wxCatalogService;

    /**
     * 在分类中展示首页索引信息
     *
     * @return com.cskaoyan.bean.BaseRespVo <com.cskaoyan.bean.vo.WxCatalogIndexVO> 返回信息
     * @author yn1609853@163.com
     * @since 2022/6/29 9:07
     */

    @RequestMapping("index")
    public BaseRespVo showCatalogIndex(){
        WxCatalogIndexVO wxCatalogIndexVO = wxCatalogService.showCatalogIndex();
        return BaseRespVo.ok(wxCatalogIndexVO);
    }

}
