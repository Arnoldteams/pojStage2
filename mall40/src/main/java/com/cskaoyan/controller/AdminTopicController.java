package com.cskaoyan.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.bo.AdminTopicCreateBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminTopicReadVO;
import com.cskaoyan.service.AdminTopicService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
     *
     * @param baseInfo 分页、排序条件
     * @param subtitle 专题子标题
     * @param title    专题标题
     * @return com.cskaoyan.bean.BaseRespVo 返回信息
     * @author xyg2597@163.com
     * @since 2022/06/25 20:05
     */
    @GetMapping("list")
    public BaseRespVo topicList(BaseParam baseInfo, String subtitle, String title) {

        CommonData<MarketTopic> commonData = adminTopicService.getList(baseInfo, subtitle, title);

        return BaseRespVo.ok(commonData);
    }

    /**
     * 添加专题
     *
     * @param adminTopicCreateBO 接收JSON数据
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/25 23:14
     */
    @RequestMapping("create")
    public BaseRespVo topicCreate(@RequestBody AdminTopicCreateBO adminTopicCreateBO) {

        MarketTopic marketTopic = new MarketTopic();

        marketTopic.setContent(adminTopicCreateBO.getContent());
        marketTopic.setTitle(adminTopicCreateBO.getTitle());
        marketTopic.setSubtitle(adminTopicCreateBO.getSubtitle());
        marketTopic.setPrice(adminTopicCreateBO.getPrice());
        marketTopic.setPicUrl(adminTopicCreateBO.getPicUrl());
        marketTopic.setReadCount(adminTopicCreateBO.getReadCount());
        marketTopic.setGoods(Arrays.toString(adminTopicCreateBO.getGoods()));


        MarketTopic marketTopicVo = adminTopicService.topicCreate(marketTopic);

        return BaseRespVo.ok(marketTopicVo);


    }

    /**
     * 编辑专题的信息回显
     * @param id 需要编辑的专题的id
     * @return com.cskaoyan.bean.BaseRespVo 返回对应专题的信息
     * @author xyg2597@163.com
     * @since 2022/06/26 13:22
     */
    @GetMapping("read")
    public BaseRespVo topicRead(Integer id) {

        AdminTopicReadVO adminTopicReadVO = adminTopicService.readTopic(id);

        return BaseRespVo.ok(adminTopicReadVO);
    }

    /**
     * 删除专题信息
     * @param marketTopic 接收需要删除的专题信息
     * @return com.cskaoyan.bean.BaseRespVo 返回删除成功的信息
     * @author xyg2597@163.com
     * @since 2022/06/26 14:36
     */
    @PostMapping("delete")
    public BaseRespVo topicDelete(@RequestBody MarketTopic marketTopic){

        adminTopicService.topicDelete(marketTopic);
        return BaseRespVo.ok(null);
    }

    /**
     * 批量删除专题信息
     * @param idList 需要删除的专题id
     * @return com.cskaoyan.bean.BaseRespVo 删除成功的信息，data为null
     * @author xyg2597@163.com
     * @since 2022/06/26 14:44
     */
    @PostMapping("batch-delete")
    public BaseRespVo topicBatchDelete(@RequestBody Map idList){

        List<Integer> ids = (List<Integer>) idList.get("ids");

        adminTopicService.topicBatchDelete(ids);
        return BaseRespVo.ok(null);
    }

}




























