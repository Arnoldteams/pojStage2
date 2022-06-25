package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminTopicService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专题管理
 *
 * @author xyg2597@163.com
 * @since 2022/06/25 19:48
 */
@RestController
@RequestMapping("admin/topic")
public class AdminTopicController {

    @Autowired
    AdminTopicService adminTopicService;

    /**
     * 根据条件获得专题信息
     * @param baseInfo 分页、排序条件
     * @param subtitle 专题子标题
     * @param title 专题标题
     * @return com.cskaoyan.bean.BaseRespVo 返回信息
     * @author xyg2597@163.com
     * @since 2022/06/25 20:05
     */
    @GetMapping("list")
    public BaseRespVo topicList(BaseParam baseInfo, String subtitle, String title) {

        CommonData<MarketTopic> commonData = adminTopicService.getList(baseInfo, subtitle, title);

        return BaseRespVo.ok(commonData);
    }



}




























