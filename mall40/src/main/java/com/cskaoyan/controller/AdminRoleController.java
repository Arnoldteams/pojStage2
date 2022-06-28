package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.bo.AdminPermissionsBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOptionsVo;
import com.cskaoyan.bean.vo.MarketSystemPermissionsVo;
import com.cskaoyan.bean.vo.PermissionsVo;
import com.cskaoyan.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;


/**
 * 系统管理-角色管理
 * @since 2022/06/25 19:36
 * @author Gzy
 */

@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    ServletContext context;
    @Autowired
    AdminRoleService adminRoleService;

    /**
     * @description: 系统管理-角色管理-展示所有角色
     * @parameter: BaseParam
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 20:17
     */
    @RequestMapping("list")
    public BaseRespVo adminRoleList(BaseParam param, String name) {
        CommonData<MarketRole> data = adminRoleService.queryAllRoles(param, name);
        return BaseRespVo.ok(data);
    }


    /**
     * @description: 系统管理-角色管理-删除角色
     * 获取所有管理员赋予的角色信息
     * @parameter: [role] 要删除的角色信息
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 21:37
     */
    @RequestMapping("/delete")
    public BaseRespVo adminRoleDelete(@RequestBody MarketRole role) {

        if (adminRoleService.deleteRole(role) == 0) {
            return BaseRespVo.AuthNotEnough("角色存在管理員權限，不能刪除！");
        }
        return BaseRespVo.ok(null);
    }

    /**
     * @description: 更新角色信息--跟删除一样^_^
     * @parameter: [role]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:22
     */
    @RequestMapping("/update")
    public BaseRespVo adminRoleUpdate(@RequestBody MarketRole role) {
        adminRoleService.updateRole(role);
        return BaseRespVo.ok(null);
    }


    /**
     * @description: 创建新用户
     * @parameter: [name, desc]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:25
     */
    @RequestMapping("/create")
    public BaseRespVo adminRoleCreate(@RequestBody MarketRole role) {

        // 跑到Bean类去赋值啦，这里看着很清爽
        MarketRole newRole = MarketRole.getMarketWithSomeInfo(role);
        if(adminRoleService.createRole(role) == 0){
            return BaseRespVo.invalidUsername("角色名重复！");
        }
        return BaseRespVo.ok(newRole);
    }

    /**
     * @description: 角色授权管理
     * @parameter: [rolePermissions]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:57
     */
    @GetMapping("/permissions")
    public BaseRespVo adminRolePermissions(Integer roleId) {
        List<PermissionsVo> systemPermissions;
        MarketSystemPermissionsVo permissions = new MarketSystemPermissionsVo();
        // 获取所有可选权限
        systemPermissions = adminRoleService.queryAllRolePermissions();
        // 获取指定id角色的权限
        List<String> assignedPermissions = adminRoleService.selectRoleApiById(roleId);
        // 封装对象
        permissions.setSystemPermissions(systemPermissions);
        permissions.setAssignedPermissions(assignedPermissions);
        return BaseRespVo.ok(permissions);
    }

    /**
     * @description: 更新角色权限
     * @parameter: [adminPermissions]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/26 17:41
     */
    @PostMapping("/permissions")
    public BaseRespVo adminRolePermissions(@RequestBody AdminPermissionsBo adminPermissions) {
        if (adminPermissions.getRoleId() == 1) {
            return BaseRespVo.AuthNotEnough("不能修改超級管理員！");
        }
        adminRoleService.updateRolePermissions(adminPermissions);
        return BaseRespVo.ok(null);
    }


    /**
     * @description: 管理员用户界面显示彩色小标签
     * @parameter: []
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/27 15:50
     */
    @RequestMapping("/options")
    public BaseRespVo adminRoleOptions() {
        CommonData<AdminOptionsVo> data = adminRoleService.queryAllRolesWithNoInfo();
        return BaseRespVo.ok(data);
    }
}
