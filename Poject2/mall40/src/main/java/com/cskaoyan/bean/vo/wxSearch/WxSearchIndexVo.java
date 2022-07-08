package com.cskaoyan.bean.vo.wxSearch;

import com.cskaoyan.bean.MarketKeyword;
import lombok.Data;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 17:48:35
 * @version:
 * @Description: 小程序搜索index
 */

@Data
public class WxSearchIndexVo {

    private MarketKeyword defaultKeyword;
    private List<MarketKeyword> hotKeywordList;
    private List<HistoryKeywordListEntity> historyKeywordList;

}
