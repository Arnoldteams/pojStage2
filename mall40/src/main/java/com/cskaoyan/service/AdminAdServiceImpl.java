package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAd;
import com.cskaoyan.bean.MarketAdExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketAdMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminAdServiceImpl implements AdminAdService {


    @Autowired
    MarketAdMapper marketAdMapper;



    /**
     * 展示所有广告/展示通过关键字搜索得到的广告
     *
     * @param name 广告名称关键字  content 广告内容关键字  info 分页设置
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketAd>页内信息
     * @author yn1609853@163.com
     * @since 2022/6/25 23:17
     */
    @Override
    public CommonData getAdList(String name, String content,BasePageInfo info){
        PageHelper.startPage(info.getPage(),info.getLimit());
        MarketAdExample marketAdExample = new MarketAdExample();
        marketAdExample.setOrderByClause(info.getSort()+" "+info.getOrder());
        MarketAdExample.Criteria criteria = marketAdExample.createCriteria();
//        不为空则赋值
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike('%'+name+ '%');
        }
        //        用and进行连接
        if(!StringUtils.isEmpty(content)){
            criteria.andContentLike('%'+content+ '%');
        }
        List<MarketAd> marketAds = marketAdMapper.selectByExample(marketAdExample);
        PageInfo<MarketAd> marketAdPageInfo = new PageInfo<>(marketAds);
        return CommonData.data(marketAdPageInfo);
    }




    /**
     * 修改指定广告的信息
     *
     * @param marketAd 广告信息
     * @return com.cskaoyan.bean.MarketAd 广告信息
     * @author yn1609853@163.com
     * @since 2022/6/26 13:52
     */

    @Override
    public MarketAd changeAd(MarketAd marketAd) {
        long l = System.currentTimeMillis();
        Date currentDate = new Date(l);
        marketAd.setUpdateTime(currentDate);
        int i = marketAdMapper.updateByPrimaryKeySelective(marketAd);
        //修改成功在控制台输出影响行数
        System.out.println(i);
        return marketAd;
    }

    /**
     * 添加一条广告
     *
     * @param marketAd 广告信息
     * @return com.cskaoyan.bean.MarketAd 广告信息
     * @author yn1609853@163.com
     * @since 2022/6/26 14:41
     */

    @Override
    public MarketAd addAd(MarketAd marketAd) {
        Date currentDate = new Date(System.currentTimeMillis());
        marketAd.setAddTime(currentDate);
        marketAd.setUpdateTime(currentDate);
        int i = marketAdMapper.insertSelective(marketAd);
        //插入成功在控制台输出影响行数
        System.out.println(i);
        return marketAd;
    }

    /**
     * 删除指定广告
     *
     * @param  marketAd 广告信息
     * @return java.lang.boolean
     * @author yn1609853@163.com
     * @since 2022/6/26 14:51
     */

    @Override
    public Boolean deleteAdById(MarketAd marketAd) {
        int i = marketAdMapper.deleteByPrimaryKey(marketAd.getId());
        return i > 0;
    }




}
