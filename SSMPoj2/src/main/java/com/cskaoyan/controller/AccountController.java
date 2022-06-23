package com.cskaoyan.controller;

import com.cskaoyan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月20日 20:45:36
 * @version:
 * @Description:
 */
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("transfer")
    @ResponseBody
    public String transfer(HttpServletRequest request) {
        Integer fromId = Integer.valueOf(request.getParameter("fromId"));
        Integer destId = Integer.valueOf(request.getParameter("destId"));
        Integer money = Integer.valueOf(request.getParameter("money"));

        accountService.transfer(fromId,destId,money.toString());

        return "ok";
    }
}
