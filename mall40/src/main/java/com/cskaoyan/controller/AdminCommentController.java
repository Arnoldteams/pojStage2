package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.bo.adminCommitDeleteBo.AdminCommentDeleteBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 后台评论模块
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 22:34
 */
@RestController
@RequestMapping("admin/comment")
public class AdminCommentController {

    @Autowired
    AdminCommentService adminCommentService;

    /**
     * @author: 啥也不想写的 Sssd
     * @description: 展示商品评论
     */
    @RequestMapping("list")
    public BaseRespVo adminCommentList(BaseParam baseParam, Integer userId, Integer valueId) {
        CommonData<MarketComment> data = adminCommentService.quarryAllComment(baseParam, userId, valueId);

        return BaseRespVo.ok(data);
    }

    /**
     * @author: Sssd
     * @description: 删除商品评论
     */
    @RequestMapping("delete")
    public BaseRespVo adminCommentDelete(@RequestBody AdminCommentDeleteBo bo) {
        adminCommentService.delelteCommontById(bo);
        return BaseRespVo.ok(null);
    }

}
