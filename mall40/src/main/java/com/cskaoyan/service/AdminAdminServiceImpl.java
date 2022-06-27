package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.MarketAdminExample;
import com.cskaoyan.bean.bo.AdminCreateBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminCreateVO;
import com.cskaoyan.bean.vo.AdminListSingleVO;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.mapper.MarketAdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 管理员模块接口实现
 *
 * @author xyg2597@163.com
 * @since 2022/06/26 15:45
 */
@Service
@Transactional
public class AdminAdminServiceImpl implements AdminAdminService {
    @Autowired
    MarketAdminMapper marketAdminMapper;

    @Override
    public AdminListVO adminList(BaseParam info, String username) {

//        开启分页
        PageHelper.startPage(info.getPage(), info.getLimit());

//        设置查询条件
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        marketAdminExample.setOrderByClause(info.getSort() + " " + info.getOrder());

        MarketAdminExample.Criteria or = marketAdminExample.or();
        if (!StringUtils.isEmpty(username)) {
            or.andUsernameLike("%" + username + "%");
        }

//        查询对应的管理员
        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);

        PageInfo<MarketAdmin> pageInfo = new PageInfo<>(marketAdmins);
        ArrayList<AdminListSingleVO> adminListSingleVOList = new ArrayList<>();
        for (MarketAdmin marketAdmin : marketAdmins) {

            AdminListSingleVO adminListSingleVO = AdminListSingleVO.setAdminListSingleVO(marketAdmin);

//            添加到管理员list中
            adminListSingleVOList.add(adminListSingleVO);
        }

//        将数据封装到VO中
        AdminListVO<AdminListSingleVO> adminListVO = new AdminListVO<>();
        adminListVO.setBaseParam(pageInfo);
        adminListVO.setList(adminListSingleVOList);

        return adminListVO;
    }

    /**
     * 创建管理员
     *
     * @param adminCreateBO 浏览器提供的信息
     * @return com.cskaoyan.bean.vo.AdminCreateVO
     * @author xyg2597@163.com
     * @since 2022/06/26 17:37
     */
    @Override
    public AdminCreateVO adminCreate(AdminCreateBO adminCreateBO) {

        Date date = new Date();
        MarketAdmin marketAdmin = new MarketAdmin();

        marketAdmin.setAddTime(date);
        marketAdmin.setUpdateTime(date);
        marketAdmin.setAvatar(adminCreateBO.getAvatar());
        marketAdmin.setPassword(adminCreateBO.getPassword());
        marketAdmin.setRoleIds(adminCreateBO.getRoleIds());
        marketAdmin.setUsername(adminCreateBO.getUsername());

        marketAdminMapper.insertSelective(marketAdmin);


        return AdminCreateVO.setAdminUpdateVO(marketAdmin);
    }

    /**
     * 修改管理员信息
     *
     * @param marketAdmin 浏览器提交的修改信息
     * @return com.cskaoyan.bean.vo.AdminCreateVO
     * @author xyg2597@163.com
     * @since 2022/06/26 19:26
     */
    @Override
    public void adminUpdate(MarketAdmin marketAdmin) {

        marketAdmin.setUpdateTime(new Date());

        marketAdminMapper.updateByPrimaryKeySelective(marketAdmin);

    }


    /**
     * 删除指定管理员
     *
     * @param marketAdmin 删除的管理员
     * @return void
     * @author xyg2597@163.com
     * @since 2022/06/26 20:23
     */
    @Override
    public void adminDelete(MarketAdmin marketAdmin) {

        marketAdminMapper.deleteByPrimaryKey(marketAdmin.getId());

    }
}

























