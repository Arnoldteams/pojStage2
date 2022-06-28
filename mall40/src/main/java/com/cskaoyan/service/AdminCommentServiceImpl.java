package com.cskaoyan.service;

import com.cskaoyan.bean.MarketComment;
import com.cskaoyan.bean.MarketCommentExample;
import com.cskaoyan.bean.bo.adminCommitDeleteBo.AdminCommentDeleteBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketCommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 商品评论模块的业务层
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月27日 22:40
 */
@Service
public class AdminCommentServiceImpl implements AdminCommentService{

    @Autowired
    MarketCommentMapper marketCommentMapper;

    @Override
    public CommonData<MarketComment> quarryAllComment(BaseParam baseParam, Integer userId, Integer valueId) {
        MarketCommentExample marketCommentExample = new MarketCommentExample();
        MarketCommentExample.Criteria criteria = marketCommentExample.createCriteria();

        // 查询 id, valueId 匹配
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (valueId != null) {
            criteria.andValueIdEqualTo(valueId);
        }

        criteria.andDeletedEqualTo(false);

        // 配置分页工具, 注意写前面
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        List<MarketComment> marketComments = marketCommentMapper.selectByExample(marketCommentExample);

        // 分页工具
        PageInfo<MarketComment> marketGoodsPageInfo = new PageInfo<>(marketComments);

        return CommonData.data(marketGoodsPageInfo);
    }

    @Override
    public void delelteCommontById(AdminCommentDeleteBo bo) {
        MarketCommentExample marketCommentExample = new MarketCommentExample();
        MarketCommentExample.Criteria criteria = marketCommentExample.createCriteria();
        criteria.andIdEqualTo(bo.getId());
        MarketComment marketComment = new MarketComment();
        marketComment.setDeleted(true);
        marketCommentMapper.updateByExampleSelective(marketComment, marketCommentExample);
    }
}
