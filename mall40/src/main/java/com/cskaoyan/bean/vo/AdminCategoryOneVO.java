package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.AdminCategoryOne;
import com.cskaoyan.bean.MarketCategory;
import lombok.Data;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/27 20:53
 */
@Data
public class AdminCategoryOneVO {
    Integer total;
    Integer pages;
    Integer limit;
    Integer page;
    List<AdminCategoryOne> list;
}
