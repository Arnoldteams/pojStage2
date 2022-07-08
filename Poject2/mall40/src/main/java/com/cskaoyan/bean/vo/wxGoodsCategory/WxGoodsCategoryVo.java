package com.cskaoyan.bean.vo.wxGoodsCategory;

import com.cskaoyan.bean.MarketCategory;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 15:34
 */
@Data
public class WxGoodsCategoryVo {

    private List<MarketCategory> brotherCategory;

    private MarketCategory currentCategory;

    private MarketCategory parentCategory;

}
