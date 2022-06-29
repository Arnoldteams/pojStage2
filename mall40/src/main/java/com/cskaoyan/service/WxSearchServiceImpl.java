package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.vo.wxSearch.HistoryKeywordListEntity;
import com.cskaoyan.bean.vo.wxSearch.WxSearchIndexVo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchListVO;
import com.cskaoyan.mapper.MarketCategoryMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.cskaoyan.mapper.MarketKeywordMapper;
import com.cskaoyan.mapper.MarketSearchHistoryMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Security;
import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 17:39:52
 * @version:
 * @Description: 小程序搜索功能
 */

@Service
public class WxSearchServiceImpl implements WxSearchService {
    @Autowired
    MarketKeywordMapper keywordMapper;

    @Autowired
    MarketSearchHistoryMapper searchHistoryMapper;

    @Autowired
    MarketGoodsMapper goodsMapper;

    @Autowired
    MarketCategoryMapper categoryMapper;

    @Autowired
    HttpSession session;

    /***
     * @author: 文陶
     * @createTime: 2022/06/29 23:08:44
     * @description: 搜索页面信息展示
     * @param: null
     * @return: com.cskaoyan.bean.vo.wxSearch.WxSearchIndexVo
     */
    @Override
    public WxSearchIndexVo index() {
        WxSearchIndexVo wxSearchIndexVo = new WxSearchIndexVo();
        //查询默认搜索值
        MarketKeywordExample example = new MarketKeywordExample();
        MarketKeywordExample.Criteria criteria = example.createCriteria();
        criteria.andIsDefaultEqualTo(true);
        List<MarketKeyword> marketKeywords = keywordMapper.selectByExample(example);
        if (marketKeywords == null || marketKeywords.size() == 0) {
            wxSearchIndexVo.setDefaultKeyword(new MarketKeyword());
        } else {
            wxSearchIndexVo.setDefaultKeyword(marketKeywords.get(0));
        }
        //查询热门搜索值
        MarketKeywordExample example2 = new MarketKeywordExample();
        MarketKeywordExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andIsHotEqualTo(true);
        List<MarketKeyword> hotKeywordList = keywordMapper.selectByExample(example2);
        wxSearchIndexVo.setHotKeywordList(hotKeywordList);

        //查询用户搜索历史
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        Integer userId = user.getId();
        List<HistoryKeywordListEntity> historyKeywordListEntityList = searchHistoryMapper.selectKeyWordByUserId(userId);
        wxSearchIndexVo.setHistoryKeywordList(historyKeywordListEntityList);

        return wxSearchIndexVo;
    }


    /***
     * @author: 文陶
     * @createTime: 2022/06/29 23:09:37
     * @description: 帮助生成搜索关键字
     * @param: keyword - [null]
     * @return: java.lang.String[]
     */
    @Override
    public String[] helper(String keyword) {
        MarketKeywordExample example = new MarketKeywordExample();
        MarketKeywordExample.Criteria criteria = example.createCriteria();
        if (keyword != null && keyword != "") {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        List<MarketKeyword> marketKeywords = keywordMapper.selectByExample(example);
        int size = marketKeywords.size();
        String[] helperList = new String[size];
        for (int i = 0; i < helperList.length; i++) {
            helperList[i] = marketKeywords.get(i).getKeyword();
        }
        return helperList;
    }

    @Override
    public void clearhistory() {
//        Subject subject = SecurityUtils.getSubject();
//        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
//        Integer userId = user.getId();
//        MarketSearchHistory history = new MarketSearchHistory();
//        history.setDeleted(true);
//        MarketSearchHistoryExample example = new MarketSearchHistoryExample();
//        MarketSearchHistoryExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(userId);
//        searchHistoryMapper.updateByExampleSelective(history,example);
    }


}
