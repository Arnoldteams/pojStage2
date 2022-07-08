package com.cskaoyan.service;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.MarketBrandExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminBrandVO;
import com.cskaoyan.mapper.MarketBrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/29 11:31
 */
@Service
public class WxBrandServiceImpl implements WxBrandService{

    @Autowired
    MarketBrandMapper marketBrandMapper;

    @Override
    public CommonData<MarketBrand> quaryAllBrand(Integer page, Integer limit) {

        MarketBrandExample example = new MarketBrandExample();
        MarketBrandExample.Criteria criteria = example.createCriteria();

        criteria.andDeletedEqualTo(false);

        //分页插件 PageHelper，辅助我们做分页以及分页信息的获得
        PageHelper.startPage(page, limit);

        List<MarketBrand> marketBrands = marketBrandMapper.selectByExample(example);

        // 将查询结果作为有参构造方法的形参传入，可以获得PageInfo
        PageInfo<MarketBrand> pageInfo = new PageInfo<>(marketBrands);
        long total = pageInfo.getTotal();

        return CommonData.data(pageInfo);
    }

    @Override
    public MarketBrand queryOneBrand(String id) {

        MarketBrand marketBrand = marketBrandMapper.selectByPrimaryKey(Integer.parseInt(id));

        return marketBrand;
    }
}
