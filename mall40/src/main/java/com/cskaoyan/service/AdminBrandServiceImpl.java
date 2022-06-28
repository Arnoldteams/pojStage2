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


import java.util.Date;
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

        // example.createCriteria().andDeletedEqualTo(false);

        criteria.andDeletedEqualTo(false); // 显示没有删除的数据

        example.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
        List<MarketBrand> marketBrands = marketBrandMapper.selectByExample(example);

        // 配置分页工具
        PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
        PageInfo<MarketBrand> pageInfo = new PageInfo<>(marketBrands);
        return CommonData.data(pageInfo);
    }

    /**
     * @Author: sprinkle
     * @createTime: 2022年06月27日 14:11:52
     * @version:
     * @Description:
     */
    @Override
    public MarketBrand updateOneBrand(MarketBrand marketBrand) {
        MarketBrandExample example = new MarketBrandExample();
        MarketBrandExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(marketBrand.getId());

        //得到当前的时间
        //用Date的构造方法来接受现在的时间（毫秒格式）
        Date date = new Date();
        // //自定义一个时间格式
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // //将date对象放入自定义格式中
        // String format = simpleDateFormat.format(date);

        //将当前时间放入marketBrand
        marketBrand.setUpdateTime(date);
        marketBrandMapper.updateByExampleSelective(marketBrand, example);
        return marketBrand;
    }

    /**
     * @Author: sprinkle
     * @createTime: 2022年06月27日 17:00:12
     * @version:
     * @Description:
     */
    @Override
    public void deleteOneBrand(MarketBrand marketBrand) {
        marketBrandMapper.deleteByPrimaryKeyUseUpdate(marketBrand.getId());
    }

    @Override
    public MarketBrand createOneBrand(MarketBrand marketBrand) {
        Date date = new Date();
        marketBrand.setAddTime(date);
        marketBrand.setUpdateTime(date);
        marketBrandMapper.insertSelective(marketBrand);
        return marketBrand;
    }

}
