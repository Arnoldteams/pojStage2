package com.cskaoyan.service;

import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.MarketGoodsExample;
import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.MarketTopicExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminTopicReadVO;
import com.cskaoyan.bean.vo.TopicReadGoodsVO;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.cskaoyan.mapper.MarketTopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 专题管理业务实现
 *
 * @author xyg2597@163.com
 * @since 2022/06/25 19:52
 */

@Service
public class AdminTopicServiceImpl implements AdminTopicService {

    @Autowired
    MarketTopicMapper marketTopicMapper;

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    /**
     * 根据条件获得专题信息
     *
     * @param baseInfo 分页设置
     * @param subtitle 专题子标题
     * @param title    专题标题
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketTopic> 返回分页的专题信息
     * @author xyg2597@163.com
     * @since 2022/06/25 20:24
     */
    @Override
    public CommonData<MarketTopic> getList(BaseParam baseInfo, String subtitle, String title) {
//        分页开启
        PageHelper.startPage(baseInfo.getPage(), baseInfo.getLimit());

        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria or = marketTopicExample.or();

//        设置模糊查询
        if (title != null) {
            title = "%" + title + "%";
            or.andTitleLike(title);
        }
        if (subtitle != null) {
            subtitle = "%" + subtitle + "%";
            or.andSubtitleLike(subtitle);
        }
        marketTopicExample.setOrderByClause(baseInfo.getSort()+" "+baseInfo.getOrder());

//        查询符合条件的专题
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExampleWithBLOBs(marketTopicExample);
//        获得页面信息
        PageInfo<MarketTopic> pageInfo = new PageInfo<>(marketTopicList);

        return CommonData.data(pageInfo);
    }

    /**
     * 添加一个新的专题
     * @param marketTopic 从浏览器获得的一些参数
     * @return com.cskaoyan.bean.MarketTopic 回显插入的专题信息
     * @author xyg2597@163.com
     * @since 2022/06/25 23:22
     */
    @Override
    public MarketTopic topicCreate(MarketTopic marketTopic) {

//        有选择的插入到数据库中
        Date date = new Date();
        marketTopic.setAddTime(date);
        marketTopic.setUpdateTime(date);
        marketTopicMapper.insertSelective(marketTopic);

        return marketTopic;
    }

    /**
     * 回显需要编辑的专题信息
     * @param id 专题id
     * @return com.cskaoyan.bean.vo.AdminTopicReadVO 需要返回专题信息的data
     * @author xyg2597@163.com
     * @since 2022/06/26 13:25
     */
    @Override
    public AdminTopicReadVO readTopic(Integer id) {

//        根据专题id查找专题信息
        MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(id);

//        获得商品id，并转化成Integer类型
        String goods = marketTopic.getGoods();
        String replace = goods.replace("[", "");
        String replace1 = replace.replace("]", "");


        String[] goodsSplit = replace1.split(",");

        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : goodsSplit) {
            integers.add(Integer.parseInt(s));
        }

//        根据商品id查询商品信息
        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria or = marketGoodsExample.or();
        or.andIdIn(integers);

        List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(marketGoodsExample);

//        对查询到的商品信息进行处理，去除不必要的信息
        ArrayList<TopicReadGoodsVO> topicReadGoodsVOS = new ArrayList<>();
        for (MarketGoods marketGood : marketGoods) {
            TopicReadGoodsVO topicReadGoodsVO = new TopicReadGoodsVO();
            topicReadGoodsVO.setAllFiled(marketGood);
            topicReadGoodsVOS.add(topicReadGoodsVO);
        }
//        封装成前端所需要的信息
        AdminTopicReadVO adminTopicReadVO = new AdminTopicReadVO();
        adminTopicReadVO.setTopic(marketTopic);
        adminTopicReadVO.setGoodsList(topicReadGoodsVOS);


        return adminTopicReadVO;
    }

    /**
     * 删除专题信息
     * @param marketTopic 该专题的信息
     * @return void 无返回值
     * @author xyg2597@163.com
     * @since 2022/06/26 14:38
     */
    @Override
    public void topicDelete(MarketTopic marketTopic) {

        marketTopicMapper.deleteByPrimaryKey(marketTopic.getId());
    }

    /**
     * 根据id列表批量删除专题信息
     * @param idList 需要删除的专题id
     * @return void
     * @author xyg2597@163.com
     * @since 2022/06/26 14:45
     */
    @Override
    public void topicBatchDelete(List<Integer> idList) {

        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria or = marketTopicExample.or();

        or.andIdIn(idList);

        marketTopicMapper.deleteByExample(marketTopicExample);
    }
}















