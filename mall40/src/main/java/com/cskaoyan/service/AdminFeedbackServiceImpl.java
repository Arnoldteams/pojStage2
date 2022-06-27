package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketFeedback;
import com.cskaoyan.bean.MarketFeedbackExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketFeedbackMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 22:06:36
 * @version:
 * @Description: admin/feedback
 */

@Service
public class AdminFeedbackServiceImpl implements AdminFeedbackService{
    @Autowired
    MarketFeedbackMapper feedbackMapper;

    @Override
    public CommonData<MarketFeedback> queryFeedback(String username, Integer id, BasePageInfo pageInfo) {
        MarketFeedbackExample example = new MarketFeedbackExample();
//        MarketFeedbackExample.Criteria criteria = example.createCriteria();
        MarketFeedbackExample.Criteria or = example.or();
        if (id != null) {
            or.andIdEqualTo(id);
        }
        if (username != null) {
            or.andUsernameLike("%"+username+"%");
        }
        example.setOrderByClause(pageInfo.getSort() + " " + pageInfo.getOrder());
        List<MarketFeedback> list = feedbackMapper.selectByExample(example);
        PageInfo<MarketFeedback> info = new PageInfo<>(list);
        return CommonData.data(info);

    }
}
