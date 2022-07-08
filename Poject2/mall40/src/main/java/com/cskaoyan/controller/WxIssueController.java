package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.wxOrder.WxOrderListChildVO;
import com.cskaoyan.service.WxIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sprinkle
 * @since 2022/06/30 09:12
 */
@RestController
@RequestMapping("wx/issue")
public class WxIssueController {

    @Autowired
    WxIssueService wxIssueService;

    @GetMapping("list")
    public BaseRespVo list(Integer page, Integer limit) {
        CommonData<MarketIssue> data = wxIssueService.queryAllIssue(page, limit);
        return BaseRespVo.ok(data);
    }
}
