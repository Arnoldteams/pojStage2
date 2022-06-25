package com.cskaoyan.service;

import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketTopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题管理业务实现
 *
 * @author xyg2597@163.com
 * @since 2022/06/25 19:52
 */

@Service
public class AdminTopicServiceImpl implements AdminTopicService {

    @Autowired
    MarketTopicMapper marketTopicMapper;

    /**
     * 根据条件获得专题信息
     * @param baseInfo 分页设置
     * @param subtitle 专题子标题
     * @param title 专题标题
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketTopic> 返回参数
     * @author xyg2597@163.com
     * @since 2022/06/25 20:24
     */
    @Override
    public CommonData<MarketTopic> getList(BaseParam baseInfo, String subtitle, String title) {
//        分页开启
        PageHelper.startPage(baseInfo.getPage(), baseInfo.getLimit());
//        查询符合条件的专题
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByConditionWithBLOBs(baseInfo.getSort(), baseInfo.getOrder(), title, subtitle);
//        获得页面信息
        PageInfo<MarketTopic> pageInfo = new PageInfo<>(marketTopicList);

        return CommonData.data(pageInfo);
    }
}















