package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketFeedback;
import com.cskaoyan.bean.param.CommonData;

public interface AdminFeedbackService {
    /**
     * @author: 文陶
     * @createTime: 2022/06/26 22:10:58
     * @description:admin/feedback/list
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketFeedback>
     */
    CommonData<MarketFeedback> queryFeedback(String username, Integer id, BasePageInfo pageInfo);
}
