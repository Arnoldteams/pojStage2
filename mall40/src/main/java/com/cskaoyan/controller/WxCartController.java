package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.bo.WxCartCheckBo;
import com.cskaoyan.bean.vo.wxCart.WxCartCheckedVo;

import com.cskaoyan.bean.vo.wxCart.WxCartIndexVo;
import com.cskaoyan.service.WxCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public BaseRespVo add(@RequestBody MarketCart cart) {
        if(wxCartService.getUserId() == -1){
            return BaseRespVo.codeAndMsg(501,"请登录！");
        }
        int number = wxCartService.cartAddNewGoodsAndReturnCount(cart);
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
    public BaseRespVo index() {
        WxCartIndexVo wxCartIndexVo = wxCartService.queryAllCartGoods();
        return BaseRespVo.ok(wxCartIndexVo);
    }

    /**
     * @description: 改变商品选中状态
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 10:52
     */
    @RequestMapping("checked")
    public BaseRespVo checked(@RequestBody Map map) {
        WxCartIndexVo wxCartIndexVo = wxCartService.updateGoodsChecked(map);
        return BaseRespVo.ok(wxCartIndexVo);
    }

    /**
     * @description: 立即购买
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 12:21
     */
    @RequestMapping("fastadd")
    public BaseRespVo fastAdd(@RequestBody MarketCart cart) {
        if(wxCartService.getUserId() == -1){
            return BaseRespVo.codeAndMsg(501,"请登录！");
        }
        int cartId = wxCartService.cartAddNewGoodsAndReturnCartId(cart);
        return BaseRespVo.ok(cartId);
    }

    /**
     * @description: 删除购物车商品
     * @parameter: [productIds]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 14:32
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map productIds) {
        WxCartIndexVo wxCartIndexVo = wxCartService.cartDeleteGoodsByProductsId(productIds);
        return BaseRespVo.ok(wxCartIndexVo);
    }

    /**
     * @description: 更新购物车商品数量
     * @parameter: [productIds]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 14:33
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody MarketCart cart){
        wxCartService.updateGoodsNumberByCartId(cart);
        return BaseRespVo.ok();
    }

    /**
     * @description: 获取购物车商品数量
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 14:44
     */
    @RequestMapping("goodscount")
    public BaseRespVo goodsCount(){
        Integer count = wxCartService.queryCartGoodsCount();
        return BaseRespVo.ok(count);
    }

    /**
     * @description: 下单前确认
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/29 16:22
     */
    @RequestMapping("checkout")
    public BaseRespVo checkOut(WxCartCheckBo cartCheckBo){
        WxCartCheckedVo cartCheckedVo = wxCartService.queryCartCheckInfo(cartCheckBo);
        return BaseRespVo.ok(cartCheckedVo);
    }

}
