package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.vo.wxSearch.HistoryKeywordListEntity;
import com.cskaoyan.bean.vo.wxSearch.WxSearchIndexVo;
import com.cskaoyan.bean.vo.wxSearch.WxSearchListVO;
import com.cskaoyan.mapper.MarketCategoryMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.cskaoyan.mapper.MarketKeywordMapper;
import com.cskaoyan.mapper.MarketSearchHistoryMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisTemplate redisTemplate;

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
        criteria.andDeletedEqualTo(false);
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
        criteria2.andDeletedEqualTo(false);
        List<MarketKeyword> hotKeywordList = keywordMapper.selectByExample(example2);
        wxSearchIndexVo.setHotKeywordList(hotKeywordList);

        //查询用户搜索历史
        //得到当前用户信息
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        //判断用户是否存在
        if (user == null) {
            List<HistoryKeywordListEntity> historyKeywordListEntityList = new ArrayList<>();
            wxSearchIndexVo.setHistoryKeywordList(historyKeywordListEntityList);
            return wxSearchIndexVo;
        }
        //用户存在，查询该用户的搜索信息
        //从redis取出最新搜索信息，存入数据库
//        String keywowrd = (String) redisTemplate.opsForValue().get("keyword");
        String keywowrd = (String) session.getAttribute("keyword");
        session.removeAttribute("keyword");
        //根据id查询用户搜索历史
        Integer userId = user.getId();
        List<HistoryKeywordListEntity> historyKeywordListEntityList = searchHistoryMapper.selectKeyWordByUserId(userId);
        if (keywowrd == null || "".equals(keywowrd)) {
            //redis没有keyword，返回查询信息
            wxSearchIndexVo.setHistoryKeywordList(historyKeywordListEntityList);
            return wxSearchIndexVo;
        }
        //redis有keyword
        //判断数据库是否已经存在该搜索关键字
        HistoryKeywordListEntity historyKeyword = new HistoryKeywordListEntity();
        historyKeyword.setKeyword(keywowrd);
        if (!historyKeywordListEntityList.contains(historyKeyword)) {
            //数据库不存在,插入
            MarketSearchHistory historyToInsert = new MarketSearchHistory();
            historyToInsert.setKeyword(keywowrd);
            historyToInsert.setUserId(userId);
            historyToInsert.setAddTime(new Date());
            historyToInsert.setUpdateTime(new Date());
            historyToInsert.setDeleted(false);
            historyToInsert.setFrom("app");
            searchHistoryMapper.insertSelective(historyToInsert);
            historyKeywordListEntityList.add(historyKeyword);
        } else {
            //已存在，修改update_time
            MarketSearchHistory historyToUpdate = new MarketSearchHistory();
            historyToUpdate.setUpdateTime(new Date());
            MarketSearchHistoryExample historyExample = new MarketSearchHistoryExample();
            MarketSearchHistoryExample.Criteria exampleCriteria = historyExample.createCriteria();
            exampleCriteria.andKeywordEqualTo(keywowrd);
            searchHistoryMapper.updateByExampleSelective(historyToUpdate, historyExample);
        }

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
        criteria.andDeletedEqualTo(false);
        List<MarketKeyword> marketKeywords = keywordMapper.selectByExample(example);
        int size = marketKeywords.size();
        String[] helperList = new String[size];
        for (int i = 0; i < helperList.length; i++) {
            helperList[i] = marketKeywords.get(i).getKeyword();
        }

        //将keyword存入redis,每次覆盖前面的value
//        redisTemplate.opsForValue().set("keyword", keyword, 300, TimeUnit.SECONDS);
//        session.setAttribute("keyword", keyword);
        return helperList;
    }

    @Override
    public void clearhistory() {
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        if (user == null) {
            return;
        }
        Integer userId = user.getId();
        //根据userId删除该用户搜索历史
        MarketSearchHistory history = new MarketSearchHistory();
        history.setDeleted(true);
        MarketSearchHistoryExample example = new MarketSearchHistoryExample();
        MarketSearchHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        searchHistoryMapper.updateByExampleSelective(history, example);
    }


}
