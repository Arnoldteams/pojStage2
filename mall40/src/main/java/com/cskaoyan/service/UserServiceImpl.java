package com.cskaoyan.service;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.param.User;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.mapper.MarketLogMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import com.cskaoyan.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stone
 * @date 2022/06/25 11:11
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MarketUserMapper marketUserMapper;

    @Autowired
    MarketLogMapper marketLogMapper;

    @Autowired
    HttpSession httpSession;


    @Override
    public CommonData<User> query(String username, BasePageInfo info) {
        PageHelper.startPage(info.getPage(), info.getLimit());
        if (username != null) {
            username = "%" + username + "%";
        }
        List<User> users = userMapper.select(username, info.getSort(), info.getOrder());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return CommonData.data(pageInfo);
    }

    @Override
    public CommonData<AdminUserListVO> queryUserList(String username,String mobile,Integer id,BasePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());

        MarketUserExample marketUserExample = new MarketUserExample();
        MarketUserExample.Criteria criteria = marketUserExample.createCriteria();
        if (username!=null && username!=""){
            criteria.andUsernameLike("%"+username+"%");
        }
        if (mobile != null && mobile != "") {
            criteria.andMobileLike("%"+mobile+"%");
        }
        if (id!=null){
            criteria.andIdEqualTo(id);
        }
        criteria.andDeletedNotEqualTo(true);
        marketUserExample.setOrderByClause(pageInfo.getSort()+" "+pageInfo.getOrder());

        List<MarketUser> list = marketUserMapper.selectByExample(marketUserExample);

        PageInfo<MarketUser> marketUserPageInfo = new PageInfo<>(list);

        return CommonData.data(marketUserPageInfo);

//        if (userListBO != null) {
//                if (userListBO.getUsername() != null) {
//                    userListBO.setUsername("%" + userListBO.getUsername() + "%");
//                }
//        }
//        List<UserEntity> userList = userMapper.selectUserList(userListBO, pageInfo.getSort(), pageInfo.getOrder());
//        PageInfo<UserEntity> pageInfoVo = new PageInfo<UserEntity>(userList);
//        AdminUserListVO userListVO = new AdminUserListVO();
//        userListVO.setLimit(pageInfoVo.getPageSize());
//        userListVO.setList(pageInfoVo.getList());
//        userListVO.setPage(pageInfoVo.getPageNum());
//        userListVO.setPages(pageInfoVo.getPages());
//        userListVO.setTotal((int) pageInfoVo.getTotal());
//        return userListVO;
    }

    @Override
    public UserEntity queryById(Integer id) {
        UserEntity user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public void updateUser(UserEntity user) {
        userMapper.updateUser(user);
        httpSession.setAttribute("log","修改用户,用户id:"+user.getId());
    }

    /**
     * 获得所有操作管理员操作信息，并封装到VO中
     *
     * @param info 分页信息
     * @param name 操作管理员名称
     * @return com.cskaoyan.bean.vo.AdminListVO
     * @author xyg2597@163.com
     * @since 2022/06/26 20:49
     */
    @Override
    public List<MarketLog> getLogList(BaseParam info, String name) {

//        开启分页插件
        PageHelper.startPage(info.getPage(), info.getLimit());

//        设置查询条件
        MarketLogExample marketLogExample = new MarketLogExample();
        marketLogExample.setOrderByClause(info.getSort() + " " + info.getOrder());

//        如果根据操作管理员名称的查询条件不为空，增加查询条件
        MarketLogExample.Criteria or = marketLogExample.or();
        if (!StringUtils.isEmpty(name)) {
            or.andAdminLike("%" + name + "%");
        }
        List<MarketLog> marketLogList = marketLogMapper.selectByExample(marketLogExample);

//        AdminListVO<MarketLog> adminListVO = new AdminListVO<>();
//        PageInfo<MarketLog> marketLogPageInfo = new PageInfo<>(marketLogList);
//
////        封装到VO中
//        adminListVO.setBaseParam(marketLogPageInfo);
//        adminListVO.setList(marketLogList);
        return marketLogList;
    }
}
