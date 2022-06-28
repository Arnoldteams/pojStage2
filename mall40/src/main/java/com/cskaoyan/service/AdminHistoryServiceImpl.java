package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketSearchHistory;
import com.cskaoyan.bean.MarketSearchHistoryExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketSearchHistoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 21:38:42
 * @version:
 * @Description: admin/history
 */

@Service
public class AdminHistoryServiceImpl implements AdminHistoryService {
    @Autowired
    MarketSearchHistoryMapper searchHistoryMapper;

    @Override
    public CommonData<MarketSearchHistory> queryHistroy(Integer userId, String keyword, BasePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        MarketSearchHistoryExample example = new MarketSearchHistoryExample();
        MarketSearchHistoryExample.Criteria or = example.or();
        if (userId != null) {
            or.andUserIdEqualTo(userId);
        }
        if (keyword != null) {
            or.andKeywordLike("%" + keyword + "%");
        }
        or.andDeletedEqualTo(false);
        example.setOrderByClause(pageInfo.getSort() + " " + pageInfo.getOrder());
        List<MarketSearchHistory> data = searchHistoryMapper.selectByExample(example);
        PageInfo<MarketSearchHistory> info = new PageInfo<>(data);
        return CommonData.data(info);
    }
}
