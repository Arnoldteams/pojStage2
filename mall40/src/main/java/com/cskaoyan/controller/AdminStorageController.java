package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminStorageVO;
import com.cskaoyan.service.AdminStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 16:01:20
 * @version: v0.1
 * @Description: 系统管理-对象存储业务（CRUD）
 */
@RestController
@RequestMapping("admin/storage")
public class AdminStorageController {

    @Autowired
    AdminStorageService adminStorageService;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 17:30:59
     * @description: 查询storage所有列
     * @param: baseParam - [BaseParam]
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminStorageVO>
     */

    @GetMapping("list")
    public BaseRespVo<AdminStorageVO> list(BaseParam baseParam) {
        BaseRespVo<AdminStorageVO> resp = new BaseRespVo<>();
        AdminStorageVO adminStorageVO = new AdminStorageVO();

        List<MarketStorage> list = adminStorageService.queryAllStorage(baseParam);

        Integer limit = baseParam.getLimit();
        Integer pages = list.size() / limit + 1;

        adminStorageVO.setPage(baseParam.getPage());
        adminStorageVO.setLimit(limit);
        adminStorageVO.setTotal(list.size());
        adminStorageVO.setPages(pages);
        adminStorageVO.setList(list);

        resp.setData(adminStorageVO);
        return resp;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 19:26:02
     * @description: 根据id更新Storage表name
     * @param: marketStorage - [MarketStorage]
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketStorage>
     */
    @PostMapping("update")
    public BaseRespVo<MarketStorage> update(@RequestBody MarketStorage marketStorage) {
        BaseRespVo<MarketStorage> resp = new BaseRespVo<>();

        marketStorage.setUpdateTime(new Date());
        adminStorageService.updateStorageById(marketStorage);
        resp.setData(marketStorage);

        return resp;
    }


    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 19:50:34
     * @description: 根据id删除Storage表中数据
     * @param: marketStorage - [MarketStorage]
     * @return: com.cskaoyan.bean.BaseRespVo<java.lang.String>
     */
    @PostMapping("delete")
    public BaseRespVo<String> delete(@RequestBody MarketStorage marketStorage) {
        BaseRespVo<String> resp = new BaseRespVo<>();

        adminStorageService.deleteKeywordById(marketStorage);

        return resp;
    }
}
