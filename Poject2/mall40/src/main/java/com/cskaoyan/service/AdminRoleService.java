package com.cskaoyan.service;


import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.MarketRolesSetPermissionsBo;
import com.cskaoyan.bean.bo.AdminPermissionsBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOptionsVo;
import com.cskaoyan.bean.vo.MarketSystemPermissionsVo;
import com.cskaoyan.bean.vo.PermissionsVo;

import java.util.List;

/**
 *  系统管理-角色管理
 *  帅关
 */
public interface AdminRoleService {

    // 查询所有角色
    CommonData queryAllRoles(BaseParam param, String name);

    // 删除指定角色
    int deleteRole(MarketRole role);

    // 更新指定角色信息
    int updateRole(MarketRole role);

    // 创建新角色
    int createRole(MarketRole role);

    // 更新角色权限
    void updateRolePermissions(AdminPermissionsBo adminPermissions);

    // 查询所有权限
    List<PermissionsVo> queryAllRolePermissions();

    // 按Id查询角色权限
    List<String> selectRoleApiById(Integer id);

    // 查询所有角色信息
    CommonData<AdminOptionsVo> queryAllRolesWithNoInfo();

    // 根据角色Id获取角色Name
    String selectRoleNameById(Integer id);

}
