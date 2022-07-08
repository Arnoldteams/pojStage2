package com.cskaoyan.service;

import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxCommentCountVO;
import com.cskaoyan.bean.vo.WxCommentVO;
/**
 * 评论页面展示
 *
 * @author sprinkle
 * @since 2022/06/29 20:42
 */
public interface WxCommentService {


    CommonData<WxCommentVO> quarryAllComment(BaseParam baseParam, byte type, Integer valueId, Integer showType);

    WxCommentCountVO getConmmentCount(Integer valueId, Integer type);

    MarketComment addTopicComment(MarketComment marketComment);

}
