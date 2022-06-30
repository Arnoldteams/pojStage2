package com.cskaoyan.service;

import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.WxAuthRegisterBO;
import com.cskaoyan.bean.vo.wx.user.UserIndexVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 15:35
 */

public interface WxAuthorService {

    //    登录后的用户首页信息
    UserIndexVO userIndex(MarketUser primaryPrincipal);

    Boolean hasAccount(String mobile);

    void insertUser(WxAuthRegisterBO wxAuthRegisterBO, String avatarUrl, HttpServletRequest request);

    Boolean updateUserByMobil(WxAuthRegisterBO wxAuthRegisterBO);

}
