package com.cskaoyan.service;

import com.cskaoyan.bean.vo.DashBoardVO;
import com.cskaoyan.mapper.MarketAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 14:27:04
 * @Description: 商城首页
 */

@Service
public class AdminServiceImpl implements AdminService {

   @Autowired
    MarketAdminMapper marketAdminMapper;

    @Override
    public DashBoardVO queryAllCount() {
        Integer goodsTotal=marketAdminMapper.selectGoodsTotal();
        Integer orderTotal=marketAdminMapper.selectOrderTotal();
        Integer productTotal=marketAdminMapper.selectProductTotal();
        Integer userTotal=marketAdminMapper.selectUserTotal();
        DashBoardVO dashBoardVO = new DashBoardVO(goodsTotal, orderTotal, productTotal, userTotal);
        return dashBoardVO;
    }
}
