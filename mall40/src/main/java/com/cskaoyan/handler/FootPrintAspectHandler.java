package com.cskaoyan.handler;

import com.cskaoyan.bean.MarketFootprint;
import com.cskaoyan.bean.MarketFootprintExample;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.mapper.MarketFootprintMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author xyg2597@163.com
 * @since 2022/06/30 11:35
 */
@Aspect
@Component
public class FootPrintAspectHandler {

    @Autowired
    MarketFootprintMapper marketFootprintMapper;

    @Pointcut("execution(public * com.cskaoyan.controller.WxGoodsController.wxGoodsDetail(*))")
    public void footPrintPointcut() {
    }

    @Around("footPrintPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = joinPoint.proceed();

        Object[] args =  joinPoint.getArgs();
//        获得商品id
        Integer goodsId = (Integer) args[0];

        MarketUser principals = (MarketUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

//        如果未登录，不记录浏览记录
        if (principals == null) {
            return proceed;
        }
//        获得用户id
        Integer userId = principals.getId();
        Date date = new Date();

//        根据用户id和商品id查询是否存在浏览记录
        MarketFootprintExample marketFootprintExample = new MarketFootprintExample();
        MarketFootprintExample.Criteria criteria = marketFootprintExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);

        List<MarketFootprint> marketFootprintList = marketFootprintMapper.selectByExample(marketFootprintExample);

//        如果没有该浏览记录，进行插入
        if (marketFootprintList.size() == 0) {
            MarketFootprint marketFootprint = new MarketFootprint();
            marketFootprint.setDeleted(false);
            marketFootprint.setUserId(userId);
            marketFootprint.setAddTime(date);
            marketFootprint.setUpdateTime(date);
            marketFootprint.setGoodsId(goodsId);

            marketFootprintMapper.insertSelective(marketFootprint);
            return proceed;
        }

//        存在该浏览记录，更新浏览记录时间
        MarketFootprint marketFootprint = marketFootprintList.get(0);
        marketFootprint.setUpdateTime(date);

        marketFootprintMapper.updateByPrimaryKeySelective(marketFootprint);

        return proceed;
    }
}
