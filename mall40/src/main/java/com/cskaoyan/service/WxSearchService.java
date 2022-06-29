package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchIndexVo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchListVO;

public interface WxSearchService {
    WxSearchIndexVo index();

    String[] helper(String keyword);


    void clearhistory();

}
