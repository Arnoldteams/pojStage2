package com.cskaoyan.service;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketOrderExample;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.MarketUserExample;
import com.cskaoyan.bean.bo.WxAuthRegisterBO;
import com.cskaoyan.bean.vo.wx.user.UserIndexOrder;
import com.cskaoyan.bean.vo.wx.user.UserIndexVO;
import com.cskaoyan.mapper.MarketOrderMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 15:36
 */

@Service
public class WxAuthorServiceImpl implements WxAuthorService {


    @Autowired
    MarketOrderMapper marketOrderMapper;

    @Autowired
    MarketUserMapper userMapper;

    /**
     * 登录后用户界面信息
     *
     * @param primaryPrincipal
     * @return com.cskaoyan.bean.vo.wx.user.UserIndexVO
     * @author xyg2597@163.com
     * @since 2022/06/29 15:54
     */
    @Override
    public UserIndexVO userIndex(MarketUser primaryPrincipal) {

        MarketOrderExample marketOrderExample = new MarketOrderExample();
        MarketOrderExample.Criteria criteria = marketOrderExample.createCriteria();
        criteria.andUserIdEqualTo(primaryPrincipal.getId());


        List<MarketOrder> marketOrders = marketOrderMapper.selectByExample(marketOrderExample);

        Integer unCommentCount = 0;
        Integer unpaidCount = 0;
        Integer unrecvCount = 0;
        Integer unshipCount = 0;
        for (MarketOrder marketOrder : marketOrders) {
            Short comments = marketOrder.getComments();
//        Comments中的0表示全部评价完成
            if (comments != 0) {
                unCommentCount++;
            }
            Short orderStatus = marketOrder.getOrderStatus();
            switch (orderStatus){
                case 101:// 表示未付款
                    unpaidCount++;
                    break;
                case 201:// 表示未发货
                    unshipCount++;
                    break;
                case 401:// 用户收货
                    unrecvCount++;
                    break;
                case 402:// 系统收货
                    unrecvCount++;
                    break;
            }
        }

//        UserIndexVO userIndexVO = new UserIndexVO();
        UserIndexOrder userIndexOrder = new UserIndexOrder();
        userIndexOrder.setUncomment(unCommentCount);
        userIndexOrder.setUnpaid(unpaidCount);
        userIndexOrder.setUnrecv(unrecvCount);
        userIndexOrder.setUnship(unshipCount);


        return UserIndexVO.setUserIndexVO(userIndexOrder);
    }

    @Override
    public Boolean hasAccount(String mobile) {

        MarketUserExample example = new MarketUserExample();
        MarketUserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile).andDeletedEqualTo(false);

        List<MarketUser> marketUsers = userMapper.selectByExample(example);

        // 如果list.size > 0 就是表中存在注册的手机号
        return marketUsers.size() > 0;
    }

    @Override
    public Boolean insertUser(WxAuthRegisterBO wxAuthRegisterBO, String avatarUrl, HttpServletRequest req) {
        MarketUser user = new MarketUser();
        user.setUsername(wxAuthRegisterBO.getUsername());
        user.setNickname(wxAuthRegisterBO.getUsername());
        user.setPassword(wxAuthRegisterBO.getPassword());
        user.setMobile(wxAuthRegisterBO.getMobile());
        user.setAvatar(avatarUrl);
        Date date = new Date();
        user.setAddTime(date);
        user.setUpdateTime(date);
        user.setLastLoginTime(date);
        user.setDeleted(false);
        user.setLastLoginIp(req.getRemoteHost());
        user.setWeixinOpenid(wxAuthRegisterBO.getWxCode());
        user.setUserLevel((byte) 0);
        user.setGender((byte) 0);
        user.setSessionKey(req.getSession().getId());
        user.setStatus((byte) 0);

        try {
            userMapper.insert(user);
        } catch (Exception e) {
            System.out.println("Err：插入失败");
            return true;
        }

        return false;
    }

    @Override
    public Boolean updateUserByMobil(WxAuthRegisterBO wxAuthRegisterBO) {

        // 判断用户是否注册了手机号
        MarketUserExample example = new MarketUserExample();
        MarketUserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(wxAuthRegisterBO.getMobile()).andDeletedEqualTo(false);

        List<MarketUser> marketUsers = userMapper.selectByExample(example);

        // 查询结果为0，就是没有注册
        if (marketUsers.size() == 0){
            return true;
        }

        // 有注册直接修改密码，更新
        MarketUser marketUser = marketUsers.get(0);
        marketUser.setPassword(wxAuthRegisterBO.getPassword());

        userMapper.updateByPrimaryKeySelective(marketUser);

        // 修改成功
        return false;
    }
}
