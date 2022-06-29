package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxCommentVO;
import com.cskaoyan.service.WxCatalogService;
import com.cskaoyan.service.WxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
