package com.cskaoyan.service;

import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.MarketBrandExample;
import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketBrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月27日 08:59:47
 * @version:
 * @Description:
 */
@Service
public class AdminBrandServiceImpl implements AdminBrandService{

    @Autowired
    MarketBrandMapper marketBrandMapper;

    @Override
    public CommonData<MarketBrand> queryAllBrand(BaseParam baseParam, String id, String name) {
        MarketBrandExample example = new MarketBrandExample();
        MarketBrandExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(id)){
            criteria.andIdEqualTo(Integer.parseInt(id));
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        criteria.andDeletedEqualTo(false); // 显示没有删除的数据

        example.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
        List<MarketBrand> marketBrands = marketBrandMapper.selectByExample(example);

        // 配置分页工具
        PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
        PageInfo<MarketBrand> pageInfo = new PageInfo<>(marketBrands);
        return CommonData.data(pageInfo);
    }
}
