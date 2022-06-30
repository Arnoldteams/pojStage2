package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.vo.wx.WxListVO;
import com.cskaoyan.service.WxCollectionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏模块业务
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 21:45
 */

@RestController
@RequestMapping("wx/collect")
public class WxCollectionController {

    @Autowired
    WxCollectionService wxCollectionService;

    /**
     * 获得用户商品收藏列表
     * @param pageInfo
     * @param type
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 21:52
     */
    @GetMapping("list")
    public BaseRespVo collectionList(BasePageInfo pageInfo, Integer type){

        Subject subject = SecurityUtils.getSubject();
        MarketUser primaryPrincipal = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();

        Integer userId = primaryPrincipal.getId();

        WxListVO wxListVO = wxCollectionService.getCollectionList(pageInfo, type, userId);

        return BaseRespVo.ok(wxListVO);
    }


    /**
     * 删除或添加商品收藏
     * @param marketCollect 封装类
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 22:54
     */
    @PostMapping("addordelete")
    public BaseRespVo addOrDelete(@RequestBody MarketCollect marketCollect){

        Subject subject = SecurityUtils.getSubject();
        MarketUser primaryPrincipal = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();

        wxCollectionService.addOrDelete(marketCollect, primaryPrincipal.getId());

        return BaseRespVo.ok();

    }
}
