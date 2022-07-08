package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketCategory;
import lombok.Data;

import java.util.List;

/**
 * 类目当前页
 *
 * @author sprinkle
 * @since 2022/06/29 15:43
 */
@Data
public class WxCatalogCurrentVO {
    MarketCategory currentCategory;
    List<MarketCategory> currentSubCategory;
}
