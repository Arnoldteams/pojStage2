package com.cskaoyan.service;

import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminTopicReadVO;

import java.util.List;

/**
 * 专题管理业务接口
 *
 * @author xyg2597@163.com
 * @since 2022/06/25 19:51
 */
public interface AdminTopicService {

//    获得专题列表信息
    CommonData<MarketTopic> getList(BaseParam baseInfo, String subtitle, String title);

//    创建一个专题
    MarketTopic topicCreate(MarketTopic marketTopic);

//    根据专题id得到对应的信息
    AdminTopicReadVO readTopic(Integer id);

//    删除对应的专题信息
    void topicDelete(MarketTopic marketTopic);

//    批量删除专题信息
    void topicBatchDelete(List<Integer> idList);

//    编辑专题信息
    MarketTopic topicUpdate(MarketTopic marketTopic);
}
