package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.WxFeedBackBo;
import com.cskaoyan.service.WxFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 07:46:35
 * @version:
 * @Description: 个人中心-意见反馈
 */
@RestController
@RequestMapping("wx/feedback")
public class WxFeedbackController {

    @Autowired
    WxFeedbackService service;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 13:22:36
     * @description: 将当前用户提交的反馈信息插入数据库
     * @param: wxFeedBackBo - [WxFeedBackBo]
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("submit")
    public BaseRespVo submit(@RequestBody WxFeedBackBo wxFeedBackBo) {

        service.insertOne(wxFeedBackBo);

        return BaseRespVo.ok();
    }

}
