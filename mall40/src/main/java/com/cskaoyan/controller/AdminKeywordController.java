package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.bo.AdminKeywordBO;
import com.cskaoyan.bean.vo.AdminKeywordVO;
import com.cskaoyan.service.AdminKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月25日 20:02:45
 * @version:v0.1
 * @Description: 处理：商场管理-关键词
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
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminKeywordVO>
     */

    @RequestMapping("list")
    public BaseRespVo<AdminKeywordVO> list(AdminKeywordBO adminKeywordBO) {

        BaseRespVo<AdminKeywordVO> resp = new BaseRespVo<>();
        AdminKeywordVO adminKeywordVO = new AdminKeywordVO();
        List<MarketKeyword> list = adminKeywordService.queryAllKeywordList(adminKeywordBO);

        Integer limit = adminKeywordBO.getLimit();
        Integer pages = list.size() / limit + 1;

        adminKeywordVO.setPage(adminKeywordBO.getPage());
        adminKeywordVO.setLimit(limit);
        adminKeywordVO.setTotal(list.size());
        adminKeywordVO.setPages(pages);
        adminKeywordVO.setList(list);

        resp.setData(adminKeywordVO);
        resp.setErrmsg("成功");
        return resp;
    }

    @RequestMapping("create")
    public BaseRespVo<MarketKeyword> create(AdminKeywordBO adminKeywordBO){
        // BaseRespVo<MarketKeyword> resp = new BaseRespVo<>();
        // MarketKeyword marketKeyword = adminKeywordService.insertKeyword(adminKeywordBO);
        //
        // resp.setData(marketKeyword);
        return null;
    }
}
