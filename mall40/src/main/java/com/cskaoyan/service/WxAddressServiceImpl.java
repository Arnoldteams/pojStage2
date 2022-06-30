package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketAddressExample;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.wxAdressBo.WxAddressSaveBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketAddressMapper;
import com.cskaoyan.mapper.MarketRegionMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
     * @param: null
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
     * @param: null
     * @return: void
     */
    @Override
    public void deleteAddressById(Integer id) {
        MarketAddressExample example = new MarketAddressExample();
        MarketAddressExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        MarketAddress address = new MarketAddress();
        address.setDeleted(true);
        addressMapper.updateByExampleSelective(address, example);
    }

    @Override
    public Integer save(WxAddressSaveBO address) {
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        if (user==null){
            return null;
        }
        //根据region信息，查询对应region id
        Integer provinceId = regionMapper.selectIdByName(address.getProvince());
        Integer cityId = regionMapper.selectIdByName(address.getCity());
        Integer countyId = regionMapper.selectIdByName(address.getCounty());
        address.setProvince(provinceId.toString());
        address.setCounty(countyId.toString());
        address.setCity(cityId.toString());

        //判断当前保存地址是否设为默认地址，如果是默认地址，将当前用户原本的默认地址改为非默认
        if (address.getIsDefault()){
            MarketAddress marketAddress = new MarketAddress();
            marketAddress.setIsDefault(false);
            MarketAddressExample example = new MarketAddressExample();
            MarketAddressExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(user.getId());
            criteria.andIsDefaultEqualTo(true);
            addressMapper.updateByExampleSelective(marketAddress,example);
        }

        //判断是保存新建还是保存修改
        if (address.getId() == 0) {
            //插入新建地址信息
            Integer id = addressMapper.insertAddress(address);
            return id;
        }
        //修改地址信息

        MarketAddress marketAddress = new MarketAddress();
        marketAddress.setName(address.getName());
        marketAddress.setTel(address.getTel());
        marketAddress.setProvince(address.getProvince());
        marketAddress.setCity(address.getCity());
        marketAddress.setCounty(address.getCounty());
        marketAddress.setAreaCode(address.getAreaCode());
        marketAddress.setAddressDetail(address.getAddressDetail());
        marketAddress.setIsDefault(address.getIsDefault());
        MarketAddressExample example = new MarketAddressExample();
        MarketAddressExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(address.getId());
        addressMapper.updateByExampleSelective(marketAddress,example);
        return address.getId();
    }


}
