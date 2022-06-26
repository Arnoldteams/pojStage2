package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketIssue;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketIssueMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZY
 * @createTime: 2022年06月25日 19:46:22
 * @Description: 商场管理-通用问题
 */

@Service
public class AdminIssueServiceImpl implements AdminIssueService {

    @Autowired
    MarketIssueMapper marketIssueMapper;


    /**
     * @author: ZY
     * @createTime: 2022/06/25 20:39:56
     * @description: 商场管理-通用问题-首页显示所有问题
     * @param: basePageInfo
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketIssue>
     */
    @Override
    public CommonData<MarketIssue> queryMarketIssue(BasePageInfo basePageInfo, String question) {
        List<MarketIssue> marketIssueList;
        if (question == null ||"".equals(question)) {
            marketIssueList = marketIssueMapper.selectAllMarketIssue();
        } else {
            question = "%" + question + "%";
            marketIssueList = marketIssueMapper.selectByPrimaryWords(basePageInfo.getSort(),basePageInfo.getOrder(),question);
        }
        PageHelper.startPage(basePageInfo.getPage(), basePageInfo.getLimit());
        PageInfo<MarketIssue> marketIssuePageInfo = new PageInfo<>(marketIssueList);
        return CommonData.data(marketIssuePageInfo);
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/25 21:12:36
     * @description: 商场管理-通用问题-新增问题
     * @param: question
     * @param: answer
     * @return: com.cskaoyan.bean.MarketIssue
     */
    @Override
    public MarketIssue addMarketIssue(String question, String answer) {
        MarketIssue marketIssue = new MarketIssue();
        marketIssue.setAddTime(new Date());
        marketIssue.setAnswer(answer);
        marketIssue.setQuestion(question);
        marketIssue.setUpdateTime(new Date());
        marketIssue.setDeleted(false);
        marketIssueMapper.insert(marketIssue);
        return marketIssue;
    }


    /**
     * @author: ZY
     * @createTime: 2022/06/25 22:15:34
     * @description: 商场管理-通用问题-删除问题(物理删除和逻辑删除）
     * @param: marketIssue
     * @return: void
     */
    @Override
    public void deleteMarketIssue(MarketIssue marketIssue) {
        Integer id = marketIssue.getId();
        marketIssueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateMarketIssueStatus(MarketIssue marketIssue) {
       marketIssueMapper.updateMarketIssueStatus(marketIssue);
    }

    /**
     * @author: ZY
     * @createTime: 2022/06/25 23:12:58
     * @description: 商场管理-通用问题-更新问题
     * @param: marketIssue
     * @return: com.cskaoyan.bean.MarketIssue
     */
    @Override
    public MarketIssue updateMarketIssue(MarketIssue marketIssue) {
        marketIssue.setUpdateTime(new Date());
       marketIssueMapper.updateByPrimaryKeySelective(marketIssue);
        return marketIssue;
    }


}
