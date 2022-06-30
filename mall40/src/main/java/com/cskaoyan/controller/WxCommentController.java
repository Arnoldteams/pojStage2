package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxCommentCountVO;
import com.cskaoyan.bean.vo.WxCommentVO;
import com.cskaoyan.service.WxCatalogService;
import com.cskaoyan.service.WxCommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论页面展示
 *
 * @author sprinkle
 * @since 2022/06/29 20:08
 */
@RestController
@RequestMapping("/wx/comment")
public class WxCommentController {

    @Autowired
    WxCommentService wxCommentService;

    /**
     * @author: sprinkle
     * @description: 评论页面展示
     */
    @RequestMapping("list")
    public BaseRespVo adminCommentList(BaseParam baseParam, byte type, Integer valueId,Integer showType) {
        CommonData<WxCommentVO> data = wxCommentService.quarryAllComment(baseParam,type,valueId,showType);

        return BaseRespVo.ok(data);
    }


    /**
     * 用户发送专题商品评论
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 10:36
     */

    @PostMapping("post")
    public BaseRespVo adminSendComment(@RequestBody MarketComment marketComment){
        Subject subject = SecurityUtils.getSubject();
        Integer userId = null;
        if (subject.isAuthenticated()) {
            MarketUser marketUser = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
            userId = marketUser.getId();
        }
        MarketComment marketComment1 = wxCommentService.addTopicComment(marketComment);
        marketComment1.setUserId(userId);
        return BaseRespVo.ok(marketComment1);
    }

    /**
     * 根据条件获得有图片和无图片的评论数量
     *
     * @param
     * @param
     * @param
     * @return
     * @author yn1609853@163.com
     * @since 2022/6/30 11:05
     */

    @GetMapping("count")
    public BaseRespVo adminCommentCount(Integer valueId,Integer type){
        WxCommentCountVO conmmentCount = wxCommentService.getConmmentCount(valueId, type);
        return BaseRespVo.ok(conmmentCount);
    }

}
