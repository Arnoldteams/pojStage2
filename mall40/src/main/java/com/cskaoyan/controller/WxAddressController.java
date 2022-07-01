package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.bo.wxAdressBo.WxAddressSaveBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.WxAddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.List;
import java.util.Map;

/**
 * @Author: 文陶
 * @createTime: 2022年06月29日 14:38:59
 * @version:
 * @Description: 小程序地址管理
 */

@RestController
@RequestMapping("wx/address")
public class WxAddressController {

    @Autowired
    WxAddressService addressService;

    @RequestMapping("list")
    public BaseRespVo list() {
        if (SecurityUtils.getSubject().getPrincipals()==null){
            return  BaseRespVo.codeAndMsg(501,"请登入");
        }
        CommonData<MarketAddress> data = addressService.queryAddressList();
        return BaseRespVo.ok(data);
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id) {
        MarketAddress data = addressService.queryAddressById(id);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map map) {
        Integer id = (Integer) map.get("id");
        addressService.deleteAddressById(id);
        return BaseRespVo.ok(null);
    }

    @RequestMapping("save")
    @LogAnnotation("修改地址")
    public BaseRespVo save(@RequestBody@Validated WxAddressSaveBO address, BindingResult bindingResult){


        if (SecurityUtils.getSubject().getPrincipals()==null){
            return  BaseRespVo.codeAndMsg(501,"请登入");
        }
        Integer addressId = addressService.save(address);
        return BaseRespVo.ok(addressId);
    }
}
