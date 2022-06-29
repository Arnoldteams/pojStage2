import com.cskaoyan.MarketApplication;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.vo.WxFootprintVO;
import com.cskaoyan.mapper.MarketFootprintMapper;
import com.cskaoyan.bean.OrderStatus;
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

    @Autowired
    MarketFootprintMapper marketFootprintMapper;


    @Test
    public void test01(){
        for (WxFootprintVO wxFootprintVO : marketFootprintMapper.selectFootprintInfoByUserId(4)) {
            System.out.println(wxFootprintVO);
        }

    }

    @Test
    public void queryAll() {
        for (MarketKeyword marketKeyword : marketKeywordMapper.selectByExample(null)) {
            System.out.println(marketKeyword.getKeyword());
        }

    }

    @Test
    public void Test(){
        Map instance = OrderStatus.getInstance();
        OrderStatus object = (OrderStatus) instance.get(1);
        System.out.println();
    }

}
