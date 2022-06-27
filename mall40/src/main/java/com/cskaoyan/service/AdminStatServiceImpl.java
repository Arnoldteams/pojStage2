package com.cskaoyan.service;

import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.MarketUserExample;
import com.cskaoyan.bean.vo.statForm.*;
import com.cskaoyan.mapper.MarketOrderGoodsMapper;
import com.cskaoyan.mapper.MarketOrderMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 22:49:18
 * @version:
 * @Description: admin/stat
 */

@Service
public class AdminStatServiceImpl implements AdminStatService {
    @Autowired
    MarketUserMapper userMapper;

    @Autowired
    MarketOrderMapper orderMapper;

    @Autowired
    MarketOrderGoodsMapper orderGoodsMapper;

    @Override
    public AdminStatUserVO statUser() {

        List<RowsEntity> rows = userMapper.countUserByAddTime();
        AdminStatUserVO statUserVO = new AdminStatUserVO();
        ArrayList<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("users");
        statUserVO.setColumns(columns);
        statUserVO.setRows(rows);

        return statUserVO;
    }

    @Override
    public AdminStatOrderVO statOrder() {
        List<OrderRowsEntity> rows = orderMapper.countOrderByAddTime();
        AdminStatOrderVO statOrderVO = new AdminStatOrderVO();
        ArrayList<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("customers");
        columns.add("amount");
        columns.add("pcr");
        statOrderVO.setColumns(columns);
        statOrderVO.setRows(rows);

        return statOrderVO;
    }

    @Override
    public AdminStatGoodsVO statGoods() {
        List<GoodsRowsEntity> rows = orderGoodsMapper.countOrderGoodsByAddTime();
        ArrayList<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("products");
        columns.add("amount");
        AdminStatGoodsVO statGoodsVO = new AdminStatGoodsVO();
        statGoodsVO.setColumns(columns);
        statGoodsVO.setRows(rows);

        return statGoodsVO;
    }
}
