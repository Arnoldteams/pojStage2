package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.wx.topic.WxTopicDetailVO;
import com.cskaoyan.bean.vo.wx.topic.WxTopicListVO;
import com.cskaoyan.service.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专题业务
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 17:10
 */
@RestController
@RequestMapping("wx/topic")
public class WxTopicController {

    @Autowired
    WxTopicService wxTopicService;

    /**
     * 获得所有的专题，并显示给前端
     * @param pageInfo 分页信息
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 17:17
     */
    @GetMapping("list")
    public BaseRespVo topicList(BasePageInfo pageInfo){

        WxTopicListVO topicListVO = wxTopicService.getTopicList(pageInfo);

        return BaseRespVo.ok(topicListVO);
    }

    /**
     * 获得指定商品专题的详细信息
     * @param id 商品专题id
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 19:18
     */
    @GetMapping("detail")
    public BaseRespVo topicDetail(Integer id){

        WxTopicDetailVO wxTopicDetailVO = wxTopicService.topicDetail(id);

        return BaseRespVo.ok(wxTopicDetailVO);
    }

    /**
     * 显示指定商品专题页面下的推荐专题
     * @param id 指定商品专题id
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 19:32
     */
    @GetMapping("related")
    public BaseRespVo topicRelated(Integer id){

        WxTopicListVO wxTopicListVO = wxTopicService.topicRelated(id);

        return BaseRespVo.ok(wxTopicListVO);
    }
}




















