package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.WxHomeAboutVO;
import com.cskaoyan.bean.vo.wxHomeIndex.WxHomeIndexVo;
import com.cskaoyan.service.WxHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 小程序主页
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月28日 21:49
 */
@RestController
@RequestMapping("/wx/home")
public class WxHomeController {

    @Autowired
    WxHomeService wxHomeService;

    /**
     * @author: 极其帅气的 Sssd
     * @description: 显示主页信息
     */
    @RequestMapping("index")
    public BaseRespVo homeIndex() {
        WxHomeIndexVo vo = wxHomeService.homeIndex();
        return BaseRespVo.ok(vo);
    }

    /**
     * @description: 小程序主页
     * @author: 丑陋的 sprinkle
     * @date: 2022年06月29日 23:17
     */
    @RequestMapping("about")
    public BaseRespVo about() {
        WxHomeAboutVO vo = wxHomeService.getAbout();
        return BaseRespVo.ok(vo);
    }
}
