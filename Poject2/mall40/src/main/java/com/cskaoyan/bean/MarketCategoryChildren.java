package com.cskaoyan.bean;

import lombok.Data;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/27 22:14
 */
@Data
public class MarketCategoryChildren {
    private Integer id;

    private String name;

    private String keywords;

    private String desc;

    private Integer pid;

    private String iconUrl;

    private String picUrl;

    private String level;

    private List<MarketCategory> children;
}
