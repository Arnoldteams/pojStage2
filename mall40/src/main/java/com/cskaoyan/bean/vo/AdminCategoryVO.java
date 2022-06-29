package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryChildren;
import lombok.Data;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/27 19:54
 */
@Data
public class AdminCategoryVO {
    Integer total;
    Integer pages;
    Integer limit;
    Integer page;
    List<MarketCategoryChildren> list;
}
