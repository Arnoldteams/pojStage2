package com.cskaoyan.service;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketCollectExample;
import com.cskaoyan.bean.MarketGoods;
import com.cskaoyan.bean.vo.wx.WxListVO;
import com.cskaoyan.bean.vo.wx.collection.UserCollectionVO;
import com.cskaoyan.mapper.MarketCollectMapper;
import com.cskaoyan.mapper.MarketGoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏模块业务实现
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 21:47
 */
@Service
public class WxCollectionServiceImpl implements WxCollectionService {

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Autowired
    MarketCollectMapper marketCollectMapper;


    @Override
    public WxListVO getCollectionList(BasePageInfo pageInfo, Integer type, Integer userId) {

//        开启分页
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());

//        设置查询条件，获得用户的收藏列表
        MarketCollectExample marketCollectExample = new MarketCollectExample();
        MarketCollectExample.Criteria criteria = marketCollectExample.createCriteria();
        marketCollectExample.setOrderByClause("update_time desc");
        criteria
                .andUserIdEqualTo(userId)
                .andTypeEqualTo((byte) type.intValue())
                .andDeletedEqualTo(false);

        List<MarketCollect> marketCollects = marketCollectMapper.selectByExample(marketCollectExample);

//        设置页面信息
        PageInfo<MarketCollect> marketCollectPageInfo = new PageInfo<>(marketCollects);
//        封装进list对象
        ArrayList<UserCollectionVO> userCollectionVOList = new ArrayList<>();
//        传递给前端的需要 封装的数据
        WxListVO<UserCollectionVO> userCollectionVOWxListVO = new WxListVO<>();
        userCollectionVOWxListVO.setBaseInfo(marketCollectPageInfo);

//        如果用户没有收藏商品，直接返回空的列表
        if (marketCollects.size() == 0) {
            userCollectionVOWxListVO.setList(userCollectionVOList);
            return userCollectionVOWxListVO;
        }

//        查询对应的商品
        for (MarketCollect marketCollect : marketCollects) {
            Integer valueId = marketCollect.getValueId();
            MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(valueId);

//            封装进VO中
            UserCollectionVO userCollectionVO = new UserCollectionVO();
            userCollectionVO.setCollectionInfo(marketCollect);
            userCollectionVO.setGoodsInfo(marketGoods);

            userCollectionVOList.add(userCollectionVO);
        }

        userCollectionVOWxListVO.setList(userCollectionVOList);

        return userCollectionVOWxListVO;
    }

    /**
     * 添加或删除商品收藏
     *
     * @param marketCollect 更新收藏
     * @return void
     * @author xyg2597@163.com
     * @since 2022/06/29 22:55
     */
    @Override
    public void addOrDelete(MarketCollect marketCollect, Integer userId) {

//        设置查询条件
        MarketCollectExample marketCollectExample = new MarketCollectExample();
        MarketCollectExample.Criteria criteria = marketCollectExample.createCriteria();
        criteria
                .andTypeEqualTo(marketCollect.getType())
                .andValueIdEqualTo(marketCollect.getValueId());

//        查询对应的收藏状态
        List<MarketCollect> marketCollects = marketCollectMapper.selectByExample(marketCollectExample);
        Date date = new Date();

//        如果没有收藏该商品，增加收藏
        if (marketCollects.size() == 0) {
            MarketCollect insertMarketCollect = new MarketCollect();

            insertMarketCollect.setDeleted(false);
            insertMarketCollect.setType(marketCollect.getType());
            insertMarketCollect.setAddTime(date);
            insertMarketCollect.setUpdateTime(date);
            insertMarketCollect.setUserId(userId);
            insertMarketCollect.setValueId(marketCollect.getValueId());

            marketCollectMapper.insertSelective(insertMarketCollect);
            return;
        }
//        通过取反，实现删除和添加
        MarketCollect selectMarketCollect = marketCollects.get(0);
        marketCollect.setDeleted(!selectMarketCollect.getDeleted());
        marketCollect.setUpdateTime(date);

        marketCollectMapper.updateByExampleSelective(marketCollect, marketCollectExample);

    }
}
