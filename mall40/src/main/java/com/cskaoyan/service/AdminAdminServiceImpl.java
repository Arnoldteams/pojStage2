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
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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

    @Autowired
    HttpSession session;

    /**
     * 回显管理员列表
     *
     * @param info     分页信息
     * @param username 查询管理员名称
     * @return com.cskaoyan.bean.vo.AdminListVO
     * @author xyg2597@163.com
     * @since 2022/06/28 14:21
     */
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

        or.andDeletedEqualTo(false);

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

        session.setAttribute("log", adminCreateBO.getUsername());

        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria criteria = marketAdminExample.createCriteria();
        criteria.andUsernameEqualTo(marketAdmin.getUsername());
        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);

        if (marketAdmins.size() != 0) {
            return null;
        }

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
    public String adminUpdate(MarketAdmin marketAdmin) {

        marketAdmin.setUpdateTime(new Date());

        session.setAttribute("log", marketAdmin.getUsername());

        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria criteria = marketAdminExample.createCriteria();
        criteria.andUsernameEqualTo(marketAdmin.getUsername());

        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);

        if(marketAdmins.size() == 0){
            return null;
        }
        marketAdminMapper.updateByPrimaryKeySelective(marketAdmin);

        return "update";
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

        MarketAdmin deleteMarketAdmin = new MarketAdmin();
        deleteMarketAdmin.setDeleted(true);
        deleteMarketAdmin.setId(marketAdmin.getId());

        session.setAttribute("log", marketAdmin.getUsername());
        marketAdminMapper.updateByPrimaryKeySelective(deleteMarketAdmin);

    }
}

























