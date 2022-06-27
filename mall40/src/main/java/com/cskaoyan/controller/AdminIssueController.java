package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author:ZY
 * @createTime: 2022年06月25日 19:42:15
 * @Description: 商场管理-通用问题
 */

@RestController
@RequestMapping("admin/issue")
public class AdminIssueController {

    @Autowired
    AdminIssueService marketIssueService;

    /**
     * @author: ZY
     * @createTime: 2022/06/25 22:19:49
     * @description: 显示首页问答
     * @param: basePageInfo
     * @param: question
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("list")
    public BaseRespVo list(BasePageInfo basePageInfo, String question) {
        CommonData<MarketIssue> data = marketIssueService.queryMarketIssue(basePageInfo, question);
        return BaseRespVo.ok(data);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/25 22:22:13
     * @description: 新增问答
     * @param: marketIssueCreateBO
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody MarketIssue marketIssue) {
        MarketIssue data =
                marketIssueService.addMarketIssue(marketIssue.getQuestion(), marketIssue.getAnswer());
        return BaseRespVo.ok(data);
    }

    /**
     * @author:ZY
     * @createTime: 2022/06/25 22:22:33
     * @description: 删除问答
     * @param: marketIssue
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody MarketIssue marketIssue) {
        //marketIssueService.deleteMarketIssue(marketIssue);
        marketIssueService.updateMarketIssueStatus(marketIssue);
        return new BaseRespVo();
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/25 23:43:15
     * @description: 更新问答
     * @param: marketIssue
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody MarketIssue marketIssue) {
        MarketIssue data = marketIssueService.updateMarketIssue(marketIssue);
        return BaseRespVo.ok(data);
    }

}
