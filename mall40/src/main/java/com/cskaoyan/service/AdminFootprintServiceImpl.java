package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketFootprint;
import com.cskaoyan.bean.MarketFootprintExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketFootprintMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 21:00:42
 * @version:
 * @Description: admin/footprint
 */

@Service
public class AdminFootprintServiceImpl implements AdminFootprintService {
    @Autowired
    MarketFootprintMapper footprintMapper;

    @Override
    public CommonData<MarketFootprint> queryFootprint(Integer userId, Integer goodsId, BasePageInfo pageInfo) {
        MarketFootprintExample example = new MarketFootprintExample();
        MarketFootprintExample.Criteria or = example.or();
        if (userId != null) {
            or.andUserIdEqualTo(userId);
        }
        if (goodsId != null) {
            or.andGoodsIdEqualTo(goodsId);
        }
        example.setOrderByClause(pageInfo.getSort() + " " + pageInfo.getOrder());
        List<MarketFootprint> list = footprintMapper.selectByExample(example);
        PageInfo<MarketFootprint> info = new PageInfo<>(list);
        return CommonData.data(info);
    }
}
