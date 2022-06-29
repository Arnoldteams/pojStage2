package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
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

    @RequestMapping("index")
    public BaseRespVo homeIndex() {
        WxHomeIndexVo vo = wxHomeService.homeIndex();
        return BaseRespVo.ok(vo);
    }
}
