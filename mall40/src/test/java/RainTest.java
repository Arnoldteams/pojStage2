import com.cskaoyan.MarketApplication;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.mapper.MarketKeywordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月27日 10:58:47
 * @version:
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RainTest {


    @Autowired
    MarketKeywordMapper marketKeywordMapper;

    @Test
    public void queryAll() {
        for (MarketKeyword marketKeyword : marketKeywordMapper.selectByExample(null)) {
            System.out.println(marketKeyword.getKeyword());
        }

    }

}
