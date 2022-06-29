package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.MarketCategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-28 22:36:26
 * @version:
 * @Description: 类目首页
 */

@Data
public class WxCatalogIndexVO {
    MarketCategory currentCategory;
    List<MarketCategory> categoryList;
    List<MarketCategory> currentSubCategory;

}
