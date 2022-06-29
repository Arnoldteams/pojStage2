import com.cskaoyan.MarketApplication;
import com.cskaoyan.bean.MarketKeyword;

import com.cskaoyan.mapper.MarketKeywordMapper;
import com.cskaoyan.service.FileService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: 于艳帆
 * @createTime: 2022年06月27日 10:58:47
 * @version:
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketApplication.class)
public class RainTest {

    @Autowired
    FileService fileService;

    @Autowired
    MarketKeywordMapper marketKeywordMapper;


    @Test
    public void test2(){
        for (int i = 0; i < 10; i++) {
            String s = RandomStringUtils.randomNumeric(6);
            System.out.println(s);
        }

    }

    @Test
    public void test1(){
        // for (MarketKeyword marketKeyword : marketKeywordMapper.selectByExample(null)) {
        //     System.out.println(marketKeyword);
        // }

        fileService.sendMsg("15602109920","123456");
    }

}
