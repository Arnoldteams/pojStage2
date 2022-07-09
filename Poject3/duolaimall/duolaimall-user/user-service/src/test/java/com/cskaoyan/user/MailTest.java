package com.cskaoyan.user;

import com.cskaoyan.mall.commons.result.ResponseData;
import com.cskaoyan.user.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther cskaoyan
 * @date 2022/4/24:15:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {



    @Autowired
    JavaMailSender sender;
    @Test
    public void test() {
        System.out.println(sender);
    }

    @Autowired
    UserController userController;
    @Test
    public void userLogin(){
        Map map = new HashMap();
        map.put("userName","cskaoyan01");
        map.put("userPwd","4742af48858b0d267cffd80d3b4846f6");
        ResponseData responseData = userController.userLogin(map);
        System.out.println(responseData);
    }
}
