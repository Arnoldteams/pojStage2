package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminBrandVO;
import com.cskaoyan.bean.vo.AdminStorageVO;
import com.cskaoyan.service.AdminBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cskaoyan.bean.MarketBrand;


/**
 * @Author: 于艳帆
 * @createTime: 2022年06月27日 08:50:15
 * @version:
 * @Description: 商场管理-品牌制造商
 */
@RestController
@RequestMapping("admin/brand")
public class AdminBrandController {

    @Autowired
    AdminBrandService adminBrandService;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-27 09:09:15
     * @description: 根据id，name查询brand表的全部匹配数据
     * @param: baseParam - [BaseParam] 分页信息
     * @param: id - [Integer] brand 的id
     * @param: name - [String] brand 的name
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminBrandVO>
     */
    @GetMapping("list")
    public BaseRespVo<AdminBrandVO> list(BaseParam baseParam, String id, String name) {

        CommonData<MarketBrand> data = adminBrandService.queryAllBrand(baseParam, id, name);

        return BaseRespVo.ok(data);
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 13:55:26
     * @description: 根据MarketBrand类的成员变量更新数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("update")
    public BaseRespVo update(@RequestBody MarketBrand marketBrand) {
        MarketBrand brand = adminBrandService.updateOneBrand(marketBrand);
        return BaseRespVo.ok(brand);
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 16:45:24
     * @description: 根据MarketBrand类的成员变量删除数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody MarketBrand marketBrand) {
        adminBrandService.deleteOneBrand(marketBrand);
        return BaseRespVo.ok();
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 16:45:24
     * @description: 根据MarketBrand类的成员变量创建数据库里的一条数据
     * @param: MarketBrand - [MarketBrand] MarketBrand类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("create")
    public BaseRespVo create(@RequestBody MarketBrand marketBrand) {
        MarketBrand brand = adminBrandService.createOneBrand(marketBrand);
        return BaseRespVo.ok(brand);
    }
}
