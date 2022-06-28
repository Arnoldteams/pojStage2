package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketAddressExample;
import com.cskaoyan.bean.MarketRegion;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketAddressMapper;
import com.cskaoyan.mapper.MarketRegionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月26日 17:43:30
 * @version:
 * @Description:admin/address
 */

@Service
public class AdminAddressServiceImpl implements AdminAddressService {
    @Autowired
    MarketAddressMapper addressMapper;
    @Autowired
    MarketRegionMapper regionMapper;

    @Override
    public CommonData<MarketAddress> queryAddress(String name, Integer userId, BasePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        MarketAddressExample example = new MarketAddressExample();
        MarketAddressExample.Criteria or = example.or();
        if (name != null) {
            name = "%" + name + "%";
            or.andNameLike(name);
        }
        if (userId !=null){
            or.andIdEqualTo(userId);
        }
        or.andDeletedEqualTo(false);
        String orderByClause = pageInfo.getSort()+" " +pageInfo.getOrder();
        example.setOrderByClause(orderByClause);
        List<MarketAddress> addressList =addressMapper.selectByExample(example);
        for (MarketAddress address : addressList) {
            String province = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getProvince())).getName();
            String city = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getCity())).getName();
            String country = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getCounty())).getName();
            address.setProvince(province);
            address.setCity(city);
            address.setCounty(country);
        }
        PageInfo<MarketAddress> addressPageInfo = new PageInfo<>(addressList);
        return CommonData.data(addressPageInfo);
    }
}
