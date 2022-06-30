package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.bo.AdminKeywordBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminKeywordVO;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminKeywordService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:02:45
 * @version:v0.1
 * @Description: 处理：商场管理-关键词业务（CRUD）
 */
@RestController
@RequestMapping("admin/keyword")
public class AdminKeywordController {

    @Autowired
    AdminKeywordService adminKeywordService;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-25 20:56:27
     * @description: 响应Keyword表的全部数据
     * @param: adminKeywordBO - [AdminKeywordBO]
     */
    @GetMapping("list")
    @RequiresPermissions(value = {"admin:keyword:list","*"},logical = Logical.OR)
    public BaseRespVo list(AdminKeywordBO adminKeywordBO) {

        CommonData<MarketKeyword> data = adminKeywordService.queryAllKeywordList(adminKeywordBO);

        return BaseRespVo.ok(data);
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-25 22:10:15
     * @description: 插入一条数据，并返回对应的PO
     * @param: adminKeywordBO - [AdminKeywordBO]
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketKeyword>
     */
    @PostMapping("create")
    @RequiresPermissions(value = {"admin:keyword:create","*"},logical = Logical.OR)
    @LogAnnotation(value = "插入keyword")
    public BaseRespVo<MarketKeyword> create(@RequestBody AdminKeywordBO adminKeywordBO, BindingResult bindingResult) {

        BaseRespVo<MarketKeyword> resp = new BaseRespVo<>();
        MarketKeyword marketKeyword = adminKeywordService.insertKeyword(adminKeywordBO);

        resp.setData(marketKeyword);
        return resp;
    }


    /**
     * @author: 于艳帆
     * @createTime: 2022-06-25 23:30:46
     * @description: 修改一条数据，并返回对应的PO
     * @param: marketKeyword - [MarketKeyword]
      * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketKeyword>
    */
    @PostMapping("update")
    @RequiresPermissions(value = {"admin:keyword:update","*"},logical = Logical.OR)
    @LogAnnotation(value = "更新keyword")
    public BaseRespVo<MarketKeyword> update(@RequestBody MarketKeyword marketKeyword, BindingResult bindingResult){
        BaseRespVo<MarketKeyword> resp = new BaseRespVo<>();

        marketKeyword.setUpdateTime(new Date());
        adminKeywordService.updateKeywordById(marketKeyword);
        resp.setData(marketKeyword);

        return resp;
    }

    @PostMapping("delete")
    @RequiresPermissions(value = {"admin:keyword:delete","*"},logical = Logical.OR)
    @LogAnnotation(value = "删除keyword")
    public BaseRespVo<String> delete(@RequestBody MarketKeyword marketKeyword, BindingResult bindingResult){
        BaseRespVo<String> resp = new BaseRespVo<>();

        adminKeywordService.deleteKeywordById(marketKeyword);

        return resp;
    }

}
