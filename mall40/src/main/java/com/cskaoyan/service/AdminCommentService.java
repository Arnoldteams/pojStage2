package com.cskaoyan.service;

import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.bo.adminCommitDeleteBo.AdminCommentDeleteBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;

public interface AdminCommentService {
    /**
     * @author: Sssd
     * @description: 展示商品评论
     */
    CommonData<MarketComment> quarryAllComment(BaseParam baseParam, Integer userId, Integer valueId);

    /**
     * @author: Sssd
     * @description: 删除商品评论
     */
    void delelteCommontById(AdminCommentDeleteBo bo);
}
