package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketBrand;
import lombok.Data;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月27日 08:53:52
 * @version:
 * @Description:
 */
@Data
public class AdminBrandVO {
    Integer total;
    Integer pages;
    Integer limit;
    Integer page;
    List<MarketBrand> list;
}
