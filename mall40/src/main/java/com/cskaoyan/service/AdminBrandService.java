package com.cskaoyan.service;

import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;

import java.util.List;

public interface AdminBrandService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-27 09:10:54
     * @description: 根据id，name查询brand表的全部匹配数据
     * @param: baseParam - [BaseParam]
     * @param: id - [Integer]
     * @param: name - [String]
     */

    CommonData<MarketBrand> queryAllBrand(BaseParam baseParam, String id, String name);
}
