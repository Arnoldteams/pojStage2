package cskaoyan;

import com.cskaoyan.bean.User;
import com.cskaoyan.config.SpringConfig;
import com.cskaoyan.service.AccountService;
import com.cskaoyan.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author stone
 * @date 2022/04/14 14:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class MyTest {


    @Autowired
    // @Qualifier("accountServiceImpl")
    AccountService accountService;

    @Autowired
    UserService userService;

    @Test
    public void testAccountService(){
        accountService.transfer(1,2,"20");
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("666");
        user.setBirthday(new Date());

        userService.insertUser(user);
    }

}
