package com.cskaoyan.service;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 系统管理-商品管理
 * @author: Sssd
 * @date: 2022年06月26日 13:47
 */
@Service
public class AdminGoodsServiceImpl implements AdminGoodsService{

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Override
    public CommonData<MarketGoods> qurryAllGoods(BaseParam baseParam, Integer goodsSn, String name, Integer goodsId) {

        MarketGoodsExample example = new MarketGoodsExample();
        String orderByClause = baseParam.getSort() + " " + baseParam.getOrder();

        // 查询 deleted = 0 （false） 的所有商品
        MarketGoodsExample.Criteria or = example.or();
        or.andDeletedEqualTo(false);

        // 商品名称模糊查询
        if (name != null) {
            String sqlName = "%" + name + "%";
            or.andNameLike(sqlName);
        }

        // 商品编号查询
        if (goodsSn != null) {
            String sqlGoodsSn = "%" + goodsSn + "%";
            or.andGoodsSnLike(sqlGoodsSn);
        }

        // 商品 id 查询
        if (goodsId != null) {
            or.andIdEqualTo(goodsId);
        }

        // 设置排序语句
        example.setOrderByClause(orderByClause);

        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(example);

        // 配置分页工具
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        PageInfo<MarketGoods> marketGoodsPageInfo = new PageInfo<>(marketGoods);
        return CommonData.data(marketGoodsPageInfo);

    }
}
