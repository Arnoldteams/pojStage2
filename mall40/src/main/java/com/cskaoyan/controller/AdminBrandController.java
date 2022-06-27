package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminBrandVO;
import com.cskaoyan.bean.vo.AdminStorageVO;
import com.cskaoyan.service.AdminBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cskaoyan.bean.MarketBrand;

import java.util.List;

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
        BaseRespVo<AdminBrandVO> resp = new BaseRespVo<>();

        AdminBrandVO adminBrandVO = new AdminBrandVO();

        List<MarketBrand> list = adminBrandService.queryAllBrand(baseParam, id, name);

        Integer limit = baseParam.getLimit();
        Integer pages = list.size() / limit + 1;

        adminBrandVO.setPage(baseParam.getPage());
        adminBrandVO.setLimit(limit);
        adminBrandVO.setTotal(list.size());
        adminBrandVO.setPages(pages);
        adminBrandVO.setList(list);

        resp.setData(adminBrandVO);

        return resp;
    }

}
