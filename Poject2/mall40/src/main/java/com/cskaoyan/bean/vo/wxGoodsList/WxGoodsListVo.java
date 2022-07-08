package com.cskaoyan.bean.vo.wxGoodsList;

import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketGoods;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 16:06
 */
@Data
public class WxGoodsListVo {

    private List<MarketCategory> filterCategoryList;

    private List<MarketGoods> list;

    private Integer total;
    private Integer pages;
    private Integer limit;
    private Integer page;

}
