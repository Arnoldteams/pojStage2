package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchIndexVo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchListVO;
import com.cskaoyan.service.WxSearchService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 17:37:37
 * @version:
 * @Description: 小程序搜索
 */
@RestController
@RequestMapping("wx/search")
public class WxSearchController {
    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("index")
    public BaseRespVo index() {
        if (SecurityUtils.getSubject().getPrincipals()==null){
            return  BaseRespVo.codeAndMsg(501,"请登入");
        }
        WxSearchIndexVo data = wxSearchService.index();
        return BaseRespVo.ok(data);
    }

    @RequestMapping("helper")
    public BaseRespVo helper(String keyword) {
        String[] data = wxSearchService.helper(keyword);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("clearhistory")
    public BaseRespVo clearhistory(){
        if (SecurityUtils.getSubject().getPrincipals()==null){
            return  BaseRespVo.codeAndMsg(501,"请登入");
        }
        wxSearchService.clearhistory();
        return BaseRespVo.ok();
    }


}
