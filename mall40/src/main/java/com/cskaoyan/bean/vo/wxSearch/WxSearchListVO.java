package com.cskaoyan.bean.vo.wxSearch;

import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketGoods;
import lombok.Data;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 23:00:16
 * @version:
 * @Description: 小程序搜索listvo
 */

@Data
public class WxSearchListVO {
    private Integer total;
    private Integer pages;
    private Integer limit;
    private Integer page;
    private List<MarketGoods> list;
    private List<MarketCategory> filterCategoryList;


}
