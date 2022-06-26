package com.cskaoyan.service;

import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.MarketRoleExample;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.mapper.MarketPermissionMapper;
import com.cskaoyan.mapper.MarketRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统管理-角色管理
 * @since 2022/06/25 20:14
 * @author 帅关
 */

@Service
public class AdminRoleServiceImpl implements AdminRoleService{

    @Autowired
    MarketRoleMapper roleMapper;
    @Autowired
    MarketPermissionMapper permissionMapper;


    /**
     * @description: 显示所有-搜索 角色
     * @parameter: [param, name]
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketRole>
     * @author: 帅关
     * @createTime: 2022/6/25 21:42
     */
    @Override
    public CommonData<MarketRole> QueryAllRoles(BaseParam param, String name) {

        MarketRoleExample example = new MarketRoleExample();
        String OrderByClause = param.getSort() + " " + param.getOrder();

        // 查询deleted = false的所有角色
        MarketRoleExample.Criteria or = example.or();
        or.andDeletedEqualTo(false);

        // 角色用户名模糊查询
        if(name != null){
            String sqlName = "%" + name + "%";
            or.andNameLike(sqlName);
        }

        // 设置排序语句
        example.setOrderByClause(OrderByClause);
        List<MarketRole> roles = roleMapper.selectByExample(example);

        // 配置分页工具
        PageHelper.startPage(param.getPage(),param.getLimit());
        PageInfo<MarketRole> pageInfo = new PageInfo<>(roles);
        return CommonData.data(pageInfo);
    }


    /**
     * @description: 删除指定角色
     * 没有真正删除，只是将deleted标志位更改为true
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 21:42
     */
    @Override
    public int deleteRole(MarketRole role) {
        role.setDeleted(true);
        return roleMapper.updateByPrimaryKey(role);
    }

    /**
     * @description: 更新角色信息
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 22:16
     */
    @Override
    public int updateRole(MarketRole role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    /**
     * @description: 创建新角色
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 23:04
     */
    @Override
    public int createRole(MarketRole role) {
        return roleMapper.insertSelective(role);
    }

    // @Override
    // public int updateRolePermissions() {
    //
    //
    //     permissionMapper.insertSelective()
    // }
}
