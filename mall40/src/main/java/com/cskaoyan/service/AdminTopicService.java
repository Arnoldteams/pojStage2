package com.cskaoyan.service;

import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;

/**
 * 专题管理业务接口
 *
 * @author xyg2597@163.com
 * @since 2022/06/25 19:51
 */
public interface AdminTopicService {

    CommonData<MarketTopic> getList(BaseParam baseInfo, String subtitle, String title);
}
