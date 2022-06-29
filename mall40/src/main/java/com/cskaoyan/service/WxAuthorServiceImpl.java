package com.cskaoyan.service;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketOrderExample;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.MarketUserExample;
import com.cskaoyan.bean.vo.wx.user.UserIndexOrder;
import com.cskaoyan.bean.vo.wx.user.UserIndexVO;
import com.cskaoyan.mapper.MarketOrderMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
