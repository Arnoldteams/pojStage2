package com.cskaoyan.service;

import com.cskaoyan.bean.MarketBrandExample;
import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.MarketCommentExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.UserInfo;
import com.cskaoyan.bean.vo.WxCommentCountVO;
import com.cskaoyan.bean.vo.WxCommentVO;
import com.cskaoyan.mapper.MarketCategoryMapper;
import com.cskaoyan.mapper.MarketCommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/29 20:42
 */
@Service
public class WxCommentServiceImpl implements WxCommentService{

    @Autowired
    MarketCommentMapper marketCommentMapper;

    @Override
    public CommonData<WxCommentVO> quarryAllComment(BaseParam baseParam, byte type, Integer valueId, Integer showType) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        MarketCommentExample example = new MarketCommentExample();
        MarketCommentExample.Criteria criteria = example.createCriteria();
        MarketCommentExample.Criteria criteria1 = criteria.andTypeEqualTo((byte) 0).andValueIdEqualTo(valueId).andDeletedEqualTo(false);

        if(showType == 1){
            criteria1.andHasPictureEqualTo(true);
        }


        //分页插件 PageHelper，辅助我们做分页以及分页信息的获得
        List<MarketComment> marketComments = marketCommentMapper.selectByExample(example);
        PageInfo<MarketComment> pageInfo = new PageInfo<>(marketComments);

        List<WxCommentVO> wxCommentVOS = new ArrayList<>();
        for (MarketComment marketComment : marketComments) {
            WxCommentVO wxCommentVO = new WxCommentVO();
            wxCommentVO.setContent(marketComment.getContent());
            wxCommentVO.setAddTime(marketComment.getAddTime());
            wxCommentVO.setAdminContent(marketComment.getAdminContent());
            wxCommentVO.setPicList(marketComment.getPicUrls());

            UserInfo userInfo = marketCommentMapper.selectUserInfo(marketComment.getUserId());

            wxCommentVO.setUserInfo(userInfo);
            wxCommentVOS.add(wxCommentVO);
        }

        CommonData data = CommonData.data(pageInfo);
        data.setList(wxCommentVOS);
        return data;
    }

    @Override
    public WxCommentCountVO getConmmentCount(Integer valueId, Integer type) {
        int hasPicCount = 0;
        int allCount = 0;

        MarketCommentExample marketCommentExample = new MarketCommentExample();
        MarketCommentExample.Criteria criteria = marketCommentExample.createCriteria();
        criteria.andValueIdEqualTo(valueId).andTypeEqualTo(Byte.valueOf(String.valueOf(type))).andDeletedEqualTo(false);
        List<MarketComment> marketComments = marketCommentMapper.selectByExample(marketCommentExample);
        for (MarketComment marketComment : marketComments) {
            if(marketComment.getHasPicture()){
                hasPicCount++;
                allCount++;
            }else{
                allCount++;
            }
        }

        WxCommentCountVO wxCommentCountVO = new WxCommentCountVO();
        wxCommentCountVO.setHasPicCount(hasPicCount);
        wxCommentCountVO.setAllCount(allCount);
        return wxCommentCountVO;
    }

    @Override
    public MarketComment addTopicComment(MarketComment marketComment) {
        marketComment.setAddTime(new Date(System.currentTimeMillis()));
        marketComment.setUpdateTime(new Date(System.currentTimeMillis()));
        int i = marketCommentMapper.insertSelective(marketComment);
        System.out.println(i);
        return marketComment;
    }
}
