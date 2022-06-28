package com.cskaoyan.service;

import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.param.BaseParam;

import java.util.List;

public interface AdminBrandService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-27 09:10:54
     * @description: 根据id，name查询brand表的全部匹配数据
     * @param: baseParam - [BaseParam]
     * @param: id - [Integer]
     * @param: name - [String]
     * @return: java.util.List<com.cskaoyan.bean.MarketBrand>
     */

    List<MarketBrand> queryAllBrand(BaseParam baseParam, String id, String name);

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 14:08:34
     * @description: 根据MarketBrand类的成员变量更新数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */

    MarketBrand updateOneBrand(MarketBrand marketBrand);

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 16:59:14
     * @description: 根据MarketBrand类的成员变量删除数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    void deleteOneBrand(MarketBrand marketBrand);

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 17:16:44
     * @description: 根据MarketBrand类的成员变量新增数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    MarketBrand createOneBrand(MarketBrand marketBrand);
}
