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

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-30 08:14:17
     * @description: 根据手机号判断是否注册
     * @param: mobile - [String]
     * @return: java.lang.Boolean true 表示手机号存在
     */
    Boolean hasAccount(String mobile);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-30 08:14:53
     * @description: 插入一个新用户，因为注册没有avatar，传入个固定值
     * @param: wxAuthRegisterBO - [WxAuthRegisterBO]
     * @param: avatarUrl - [String] 头像地址
     * @param: request - [HttpServletRequest]
     * @return: void
     */
    Boolean insertUser(WxAuthRegisterBO wxAuthRegisterBO, String avatarUrl, HttpServletRequest request);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-30 08:16:06
     * @description: 根据手机号来修改密码
     * @param: wxAuthRegisterBO - [WxAuthRegisterBO]
     * @return: java.lang.Boolean true 表示手机号不存在
     */
    Boolean updateUserByMobil(WxAuthRegisterBO wxAuthRegisterBO);
}
