package com.cskaoyan.service;

import com.cskaoyan.bean.MarketRegion;
import com.cskaoyan.bean.MarketRegionExample;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.MarketRegionInfoNoChildVO;
import com.cskaoyan.bean.vo.MarketRegionInfoVO;
import com.cskaoyan.bean.vo.MarketRegionVO;
import com.cskaoyan.mapper.MarketRegionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-27 20:16:36
 * @version:
 * @Description: 商场管理-行政区域
 */

@Service
public class AdminRegionServiceImpl implements AdminRegionService {

    @Autowired
    MarketRegionMapper marketRegionMapper;

    /**
     * 获得所有行政区域信息
     *
     * @return com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.vo.MarketRegionInfoVO> 页内信息
     * @author yn1609853@163.com
     * @since 2022/6/28 10:22
     */
     
    @Override
    public CommonData getRegionList() {

        CommonData<MarketRegionInfoVO> commonData = new CommonData<>();
        commonData.setLimit(31);
        commonData.setPage(1);
        commonData.setPages(1);
        commonData.setTotal(31);

        MarketRegionExample marketRegionExample = new MarketRegionExample();
        List<MarketRegion> marketRegions = marketRegionMapper.selectByExample(marketRegionExample);

        //创建总数据表
        LinkedList<MarketRegionVO> marketRegionVOS = new LinkedList<>();

        //给总数据表赋值
        for (int i = 0; i < marketRegions.size(); i++) {
            MarketRegion marketRegion = marketRegions.get(i);
            marketRegionVOS.add(MarketRegionVO.changeToVO(marketRegion));
        }

        //创建省级列表
        LinkedList<MarketRegionInfoVO> marketRegionVOS1 = new LinkedList<>();

        //加入所有省份
        for (int i = 0; i < marketRegionVOS.size(); i++) {
            MarketRegionVO marketRegionVO = marketRegionVOS.get(i);
            Byte type = marketRegionVO.getType();
            if(type == 1){
                marketRegionVOS1.add(MarketRegionInfoVO.changeToVO(marketRegionVO));
            }
        }



        //遍历省份表，同时在里面遍历总表
        for (int i = 0; i < marketRegionVOS1.size(); i++) {
            MarketRegionInfoVO marketRegionVO1 = marketRegionVOS1.get(i);
            //为每个省份创建市级列表
            LinkedList<MarketRegionVO> marketRegionVOS3 = new LinkedList<>();
            LinkedList<MarketRegionInfoVO> marketRegionVOS5 = new LinkedList<>();

            //遍历总表
            for (int j = 0; j < marketRegionVOS.size(); j++) {
                MarketRegionVO marketRegionVO = marketRegionVOS.get(j);
                if(marketRegionVO1.getId().equals(marketRegionVO.getPid())){
                    marketRegionVOS3.add(marketRegionVO);
                    marketRegionVOS5.add(MarketRegionInfoVO.changeToVO(marketRegionVO));
                }
            }
            marketRegionVO1.setChildren(marketRegionVOS5);
        }

        //遍历省份表里面的市级表，同时在市级表里面遍历总表
        for (int i = 0; i < marketRegionVOS1.size(); i++) {
            MarketRegionInfoVO marketRegionVO = marketRegionVOS1.get(i);
            List children = marketRegionVO.getChildren();
            for (int i1 = 0; i1 < children.size(); i1++) {
                MarketRegionInfoVO marketRegionVO1 = (MarketRegionInfoVO)children.get(i1);
                //为每个市创建区级表
                LinkedList<MarketRegionInfoNoChildVO> marketRegionVOS6 = new LinkedList<>();

                //遍历总表
                for (int i2 = 0; i2 < marketRegionVOS.size(); i2++) {
                    MarketRegionVO marketRegionVO2 = marketRegionVOS.get(i2);
                    if(marketRegionVO2.getPid().equals(marketRegionVO1.getId())){

                        marketRegionVOS6.add(MarketRegionInfoNoChildVO.changeToVO(marketRegionVO2));
                    }
                }
                marketRegionVO1.setChildren(marketRegionVOS6);
            }
        }





        commonData.setList(marketRegionVOS1);

        return commonData;
    }
}
