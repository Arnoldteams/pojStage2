package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.OrderStatus;
import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;
import com.cskaoyan.service.WxCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 *  商场购物车
 * @since 2022/06/28 19:03
 * @author Gzy
 */

@RestController()
@RequestMapping("/wx/cart")
public class WxCartController {
    @Autowired
    WxCartService wxCartService;

    /**
     * @description: 添加购物车
     * @parameter: [cart]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/28 21:48
     */
    @RequestMapping("add")
    public BaseRespVo add(@RequestBody MarketCart cart){
        int number = wxCartService.cartAddNewGoods(cart);
        return BaseRespVo.ok(number);
    }

    /**
     * @description: 显示购物车首页
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/28 21:55
     */
    @RequestMapping("index")
    public BaseRespVo index(){
        WxCartIndexVo wxCartIndexVo = wxCartService.queryAllCartGoods();
        return BaseRespVo.ok(wxCartIndexVo);
    }
}
