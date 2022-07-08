package com.cskaoyan.service;


import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketGoodsExample;
import com.cskaoyan.bean.MarketTopic;
import com.cskaoyan.bean.MarketTopicExample;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.vo.AdminTopicReadVO;
import com.cskaoyan.bean.vo.TopicReadGoodsVO;
import com.cskaoyan.bean.vo.wx.topic.SingleTopicInfoVO;
import com.cskaoyan.bean.vo.wx.topic.WxTopicDetailVO;
import com.cskaoyan.bean.vo.wx.WxListVO;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.cskaoyan.mapper.MarketTopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 专题业务实现
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 17:12
 */
@Service
public class WxTopicServiceImpl implements WxTopicService {

    @Autowired
    MarketTopicMapper marketTopicMapper;

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    /**
     * 获得所有的专题信息
     *
     * @param pageInfo
     * @return com.cskaoyan.bean.vo.wx.topic.WxTopicListVO
     * @author xyg2597@163.com
     * @since 2022/06/29 19:12
     */
    @Override
    public WxListVO getTopicList(BasePageInfo pageInfo) {

        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());

//        查询未逻辑删除的专题
        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria criteria = marketTopicExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExample(marketTopicExample);

//        根据查询的内容获得页面信息
        PageInfo<MarketTopic> marketTopicPageInfo = new PageInfo<>(marketTopicList);

//        去除多余的数据
        ArrayList<SingleTopicInfoVO> singleTopicInfoVOList = new ArrayList<>();
        for (MarketTopic marketTopic : marketTopicList) {
            singleTopicInfoVOList.add(SingleTopicInfoVO.setSingleTopicInfoVO(marketTopic));
        }

//        将查询信息封装代VO中
        WxListVO<SingleTopicInfoVO> singleTopicInfoVOTopicListVO = new WxListVO<>();
        singleTopicInfoVOTopicListVO.setList(singleTopicInfoVOList);
        singleTopicInfoVOTopicListVO.setBaseInfo(marketTopicPageInfo);

        return singleTopicInfoVOTopicListVO;
    }

    /**
     * 获得某个专题的详细信息
     *
     * @param id 专题id
     * @return void
     * @author xyg2597@163.com
     * @since 2022/06/29 19:12
     */
    @Override
    public WxTopicDetailVO topicDetail(Integer id) {


//        根据专题id查找专题信息
        MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(id);


//        将专题信息封装封装成前端所需要的信息
        WxTopicDetailVO wxTopicDetailVO = new WxTopicDetailVO();
        wxTopicDetailVO.setTopic(marketTopic);

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
        wxTopicDetailVO.setGoods(topicReadGoodsVOS);

        return wxTopicDetailVO;
    }

    /**
     * 返回相关专题信息
     *
     * @param id 去除指定id的专题
     * @return com.cskaoyan.bean.vo.wx.topic.WxTopicListVO
     * @author xyg2597@163.com
     * @since 2022/06/29 19:41
     */
    @Override
    public WxListVO topicRelated(Integer id) {

//        分页开启
        PageHelper.startPage(1, 4);

//        设置查询信息，去除详情页面信息
        MarketTopicExample marketTopicExample = new MarketTopicExample();
        MarketTopicExample.Criteria criteria = marketTopicExample.createCriteria();
        criteria.andIdNotEqualTo(id);

//        设置排序信息
        marketTopicExample.setOrderByClause("sort_order");

//        查询符合条件的专题
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExampleWithBLOBs(marketTopicExample);
//        获得页面信息
        PageInfo<MarketTopic> pageInfo = new PageInfo<>(marketTopicList);

//        封装查询信息
        WxListVO<MarketTopic> topicWxTopicListVO = new WxListVO<>();
        topicWxTopicListVO.setBaseInfo(pageInfo);
        topicWxTopicListVO.setList(marketTopicList);

        return topicWxTopicListVO;

    }
}
