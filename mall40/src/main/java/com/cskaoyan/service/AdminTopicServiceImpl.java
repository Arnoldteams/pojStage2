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
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    HttpSession session;


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

        or.andDeletedEqualTo(false);
        marketTopicExample.setOrderByClause(baseInfo.getSort() + " " + baseInfo.getOrder());

//        查询符合条件的专题
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExampleWithBLOBs(marketTopicExample);
//        获得页面信息
        PageInfo<MarketTopic> pageInfo = new PageInfo<>(marketTopicList);

        return CommonData.data(pageInfo);
    }

    /**
     * 添加一个新的专题
     *
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
        session.setAttribute("log", marketTopic.getTitle());
        MarketTopic selectMarketTopic = marketTopicMapper.selectByPrimaryKey(marketTopic.getId());


        return selectMarketTopic;
    }

    /**
     * 回显需要编辑的专题信息
     *
     * @param id 专题id
     * @return com.cskaoyan.bean.vo.AdminTopicReadVO 需要返回专题信息的data
     * @author xyg2597@163.com
     * @since 2022/06/26 13:25
     */
    @Override
    public AdminTopicReadVO readTopic(Integer id) {

//        根据专题id查找专题信息
        MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(id);


//        将专题信息封装封装成前端所需要的信息
        AdminTopicReadVO adminTopicReadVO = new AdminTopicReadVO();
        adminTopicReadVO.setTopic(marketTopic);

        Integer[] goods = marketTopic.getGoods();
        ArrayList<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, goods);
//        List<Integer> integers = Arrays.asList(goods);

//        根据商品id查询商品信息
        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria or = marketGoodsExample.or();
        ArrayList<TopicReadGoodsVO> topicReadGoodsVOS = new ArrayList<>();
        if (integers.size() != 0) {
            or.andIdIn(integers);
            List<MarketGoods> marketGoods = marketGoodsMapper.selectByExample(marketGoodsExample);

//        对查询到的商品信息进行处理，去除不必要的信息
            for (MarketGoods marketGood : marketGoods) {
                TopicReadGoodsVO topicReadGoodsVO = new TopicReadGoodsVO();
                topicReadGoodsVO.setAllFiled(marketGood);
                topicReadGoodsVOS.add(topicReadGoodsVO);
            }
        }




//        将商品列表封装成前端所需要的信息
        adminTopicReadVO.setGoodsList(topicReadGoodsVOS);


        return adminTopicReadVO;
    }

    /**
     * 删除专题信息
     *
     * @param marketTopic 该专题的信息
     * @return void 无返回值
     * @author xyg2597@163.com
     * @since 2022/06/26 14:38
     */
    @Override
    public void topicDelete(MarketTopic marketTopic) {
        MarketTopic deleteMarketTopic = new MarketTopic();

        deleteMarketTopic.setDeleted(true);
        deleteMarketTopic.setId(marketTopic.getId());

        session.setAttribute("log", marketTopic.getTitle());
        marketTopicMapper.updateByPrimaryKeySelective(deleteMarketTopic);
    }

    /**
     * 根据id列表批量删除专题信息
     *
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
        MarketTopic marketTopic = new MarketTopic();
        marketTopic.setDeleted(true);

        session.setAttribute("log", idList);

        marketTopicMapper.updateByExampleSelective(marketTopic, marketTopicExample);
    }


    /**
     * 更新专题信息
     *
     * @param marketTopic 更新的专题信息
     * @return com.cskaoyan.bean.MarketTopic
     * @author xyg2597@163.com
     * @since 2022/06/27 23:19
     */
    @Override
    public MarketTopic topicUpdate(MarketTopic marketTopic) {
        //        有选择的插入到数据库中
        Date date = new Date();
        marketTopic.setUpdateTime(date);
        session.setAttribute("log", marketTopic.getTitle());
        marketTopicMapper.updateByPrimaryKeySelective(marketTopic);

        return marketTopic;
    }
}















