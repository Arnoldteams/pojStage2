package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxFootprintVO;
import com.cskaoyan.mapper.MarketFootprintMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月29日 13:27:47
 * @version:
 * @Description:
 */
@Service
public class WxFootprintServiceImpl implements WxFootprintService{

    @Autowired
    MarketFootprintMapper mapper;

    @Override
    public CommonData<WxFootprintVO> queryAllFootprint(BaseParam baseParam) {

        // 拿到用户相关信息
        Subject subject = SecurityUtils.getSubject();
        MarketUser user = (MarketUser) subject.getPrincipals().getPrimaryPrincipal();
        Integer userId = user.getId();


        // 只有拿到当前用户 id 才查询
        List<WxFootprintVO> list = null;
        if (userId != null){
            // 配置分页工具
            PageHelper.startPage(baseParam.getPage(),baseParam.getLimit());
            list  = mapper.selectFootprintInfoByUserId(userId);
        }

        PageInfo<WxFootprintVO> pageInfo = new PageInfo<>(list);

        return CommonData.data(pageInfo);
    }

    @Override
    public void deleteById(Integer id) {

        MarketFootprint newDate = new MarketFootprint();

        // 删除的id对应行，标志位true
        newDate.setId(id);
        newDate.setDeleted(true);

        mapper.updateByPrimaryKeySelective(newDate);
    }


}
