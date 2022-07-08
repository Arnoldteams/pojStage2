package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketStorage;
import lombok.Data;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 15:53:10
 * @version:
 * @Description:
 */
@Data
public class AdminStorageVO {
    Integer total;
    Integer pages;
    Integer limit;
    Integer page;
    List<MarketStorage> list;
}
