package com.cskaoyan.controller;

import com.cskaoyan.bean.Result;
import com.cskaoyan.bean.Test;
import com.cskaoyan.bean.User;
import com.cskaoyan.service.AccountService;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping("hello")
    public Result hello(HttpSession httpSession, String name, String pwd) {
        return Result.ok("hello");
    }

    @RequestMapping("hello2")
    public Result hello2(HttpSession httpSession, String name, String pwd) {
        return Result.ok("xiaofang come in");
    }

    @RequestMapping("sparkx")
    public Result helloSparkX(){
        return Result.ok("hello SparkX");
    }


    @RequestMapping("login")
    public Result login(HttpSession httpSession, String name, String pwd) {

        httpSession.setAttribute("name",name);
        httpSession.setAttribute("pwd",pwd);

        return Result.ok("登陆成功");
    }

    @RequestMapping("register")
    public Result register(User user) {

        userService.insertUser(user);

        return Result.ok(user);
    }

    @RequestMapping("query")
    public Result queryUserByLike(String content){

        List<User> users = userService.queryUsersByLike(content);

        return Result.ok(users);
    }

    @PostMapping("transfer")

    public Result transfer(@RequestBody Test test){

        accountService.transfer(test.getFrom(),test.getTo(),test.getMoney());

        return Result.ok();
    }

}
