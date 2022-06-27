package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAddressExample;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketCollectExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketCollectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 20:23:13
 * @version:
 * @Description: admin/collect
 */

@Service
public class AdminCollectServiceImpl implements AdminCollectService {
    @Autowired
    MarketCollectMapper collectMapper;


    @Override
    public CommonData<MarketCollect> queryCollect(Integer userId, Integer valueId, BasePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        MarketCollectExample example = new MarketCollectExample();
        MarketCollectExample.Criteria or = example.or();
        if (userId != null) {
            or.andUserIdEqualTo(userId);
        }
        if (valueId != null) {
            or.andValueIdEqualTo(valueId);
        }
        or.andDeletedEqualTo(false);
        example.setOrderByClause(pageInfo.getSort() + " " + pageInfo.getOrder());
        List<MarketCollect> collectList = collectMapper.selectByExample(example);
        PageInfo<MarketCollect> collectPageInfo = new PageInfo<>(collectList);
        return CommonData.data(collectPageInfo);
    }
}
