package com.cskaoyan.service;

import com.cskaoyan.bean.MarketFeedback;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.WxFeedBackBo;
import com.cskaoyan.mapper.MarketFeedbackMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 07:51:32
 * @version:
 * @Description:
 */
@Service
public class WxFeedbackServiceImpl implements WxFeedbackService {

    @Autowired
    MarketFeedbackMapper mapper;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 13:20:09
     * @description: 通过subject拿到当前user信息+wxFeedBackBo反馈的信息 = MarketUser
     *              MarketUser 插入表中
     * @param: wxFeedBackBo - [WxFeedBackBo]
     * @return: void
     */

    @Override
    public void insertOne(WxFeedBackBo wxFeedBackBo) {
        MarketFeedback marketFeedback = new MarketFeedback();

        // 拿到用户相关信息
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        marketFeedback.setUserId(user.getId());
        marketFeedback.setUsername(user.getUsername());

        marketFeedback.setAddTime(new Date());
        marketFeedback.setUpdateTime(new Date());
        marketFeedback.setDeleted(false);
        marketFeedback.setMobile(wxFeedBackBo.getMobile());
        marketFeedback.setFeedType(wxFeedBackBo.getFeedType());
        marketFeedback.setContent(wxFeedBackBo.getContent());

        // 如果反馈信息没有图片处理
        if (wxFeedBackBo.getHasPicture() != null && wxFeedBackBo.getHasPicture()) {
            marketFeedback.setHasPicture(wxFeedBackBo.getHasPicture());
            marketFeedback.setPicUrls(wxFeedBackBo.getPicUrls().get(0));
        }

        mapper.insertSelective(marketFeedback);
    }
}
