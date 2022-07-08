package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.vo.wx.user.UserIndexVO;
import com.cskaoyan.service.WxAuthorService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户业务
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 20:25
 */
@RestController
@RequestMapping("wx/")
public class WxUserController {

    @Autowired
    WxAuthorService wxAuthorService;
    /**
     * 登录后的用户首页信息
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/29 15:44
     */
    @GetMapping("user/index")
    public BaseRespVo userIndex() {

        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        if(principals == null){
            return BaseRespVo.codeAndMsg(501,"请登录");    
        }

        MarketUser primaryPrincipal = (MarketUser) principals.getPrimaryPrincipal();

        UserIndexVO userIndexVO = wxAuthorService.userIndex(primaryPrincipal);
        return BaseRespVo.ok(userIndexVO);
    }
}
