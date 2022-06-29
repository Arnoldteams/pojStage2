package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.vo.wx.topic.WxTopicDetailVO;
import com.cskaoyan.bean.vo.wx.topic.WxTopicListVO;

/**
 * 专题业务接口
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 17:12
 */

public interface WxTopicService {

//    显示所有的专题
    WxTopicListVO getTopicList(BasePageInfo pageInfo);

//    显示专题信息
    WxTopicDetailVO topicDetail(Integer id);

//    显示相关专题信息
    WxTopicListVO topicRelated(Integer id);
}
