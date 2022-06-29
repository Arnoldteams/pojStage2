package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.vo.wx.WxListVO;

/**
 * 收藏模块接口
 * @author xyg2597@163.com
 * @since 2022/06/29 21:46
 */

public interface WxCollectionService {

//    获得商品收藏列表
    WxListVO getCollectionList(BasePageInfo pageInfo, Integer type, Integer userId);

//    添加或删除商品收藏
    void addOrDelete(MarketCollect marketCollect, Integer userId);
}
