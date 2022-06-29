package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketAddressExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketAddressMapper;
import com.cskaoyan.mapper.MarketRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 14:42:59
 * @version:
 * @Description: 小程序地址管理
 */

@Service
public class WxAddressServiceImpl implements WxAddressService {
    @Autowired
    MarketAddressMapper addressMapper;

    @Autowired
    MarketRegionMapper regionMapper;

    /***
     * @author: 文陶
     * @createTime: 2022/06/29 15:55:56
     * @description: 查询地址
     * @param:  null
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketAddress>
     */
    @Override
    public CommonData<MarketAddress> queryAddressList() {

        MarketAddressExample example = new MarketAddressExample();
        MarketAddressExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<MarketAddress> marketAddressList = addressMapper.selectByExample(example);
        for (MarketAddress address : marketAddressList) {
            String province = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getProvince())).getName();
            String city = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getCity())).getName();
            String country = regionMapper.selectByPrimaryKey(Integer.parseInt(address.getCounty())).getName();
            address.setProvince(province);
            address.setCity(city);
            address.setCounty(country);
        }
        CommonData<MarketAddress> data = new CommonData<>();
        data.setLimit(marketAddressList.size());
        data.setTotal(marketAddressList.size());
        data.setPage(1);
        data.setPages(1);
        data.setList(marketAddressList);

        return data;
    }

    /***
     * @author: 文陶
     * @createTime: 2022/06/29 15:56:32
     * @description: 地址详情
     * @param: id - [null]
     * @return: com.cskaoyan.bean.MarketAddress
     */
    @Override
    public MarketAddress queryAddressById(Integer id) {
        MarketAddress address = addressMapper.selectByPrimaryKey(id);
        return address;
    }



    /***
     * @author: 文陶
     * @createTime: 2022/06/29 15:57:10
     * @description: 删除地址
     * @param:  null
     * @return: void
     */
    @Override
    public void deleteAddressById(Integer id) {
        MarketAddressExample example = new MarketAddressExample();
        MarketAddressExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        MarketAddress address = new MarketAddress();
        address.setDeleted(true);
        addressMapper.updateByExampleSelective(address,example);
    }


}
