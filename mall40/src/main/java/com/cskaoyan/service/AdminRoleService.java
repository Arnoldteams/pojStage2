package com.cskaoyan.service;


import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;

/**
 *  系统管理-角色管理
 *  帅关
 */
public interface AdminRoleService {

    // 查询所有角色
    CommonData QueryAllRoles(BaseParam param, String name);

    // 删除指定角色
    int deleteRole(MarketRole role);

    // 更新指定角色信息
    int updateRole(MarketRole role);

    // 创建新角色
    int createRole(MarketRole role);

    // 角色授权
    // int updateRolePermissions();

}
