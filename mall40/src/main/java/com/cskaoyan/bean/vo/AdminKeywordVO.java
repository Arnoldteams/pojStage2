package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketKeyword;
import lombok.Data;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:21:14
 * @version:
 * @Description:
 */
@Data
public class AdminKeywordVO {
    Integer total;
    Integer pages;
    Integer limit;
    Integer page;
    List<MarketKeyword> list;
}
