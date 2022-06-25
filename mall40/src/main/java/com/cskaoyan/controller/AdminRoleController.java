package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.MarketRolesSetPermissionsBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统管理-角色管理
 * @since 2022/06/25 19:36
 * @author Gzy
 */

@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {

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
    public BaseRespVo adminRoleList(BaseParam param,String name){
        CommonData<MarketRole> data =  adminRoleService.QueryAllRoles(param,name);
        return BaseRespVo.ok(data);
    }


    /**
     * @description: 系统管理-角色管理-删除角色
     * @parameter: [role] 要删除的角色信息
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 21:37
     */
    @RequestMapping("/delete")
    public BaseRespVo adminRoleDelete(@RequestBody MarketRole role){
        if(adminRoleService.deleteRole(role) == 1){
            return BaseRespVo.ok(null);
        }
        return BaseRespVo.invalidData("角色删除失败！");
    }

    /**
     * @description: 更新角色信息--跟删除一样^_^
     * @parameter: [role]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:22
     */
    @RequestMapping("/update")
    public BaseRespVo adminRoleUpdate(@RequestBody MarketRole role){
        if(adminRoleService.updateRole(role) == 1){
            return BaseRespVo.ok(null);
        }
        return BaseRespVo.invalidData("角色删除失败！");
    }


    /**
     * @description: 创建新用户
     * @parameter: [name, desc]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:25
     */
    @RequestMapping("/create")
    public BaseRespVo adminRoleCreate(@RequestBody MarketRole role){

        // 跑到Bean类去赋值啦，这里看着很清爽
        MarketRole newRole = MarketRole.getMarketWithSomeInfo(role);
        if(adminRoleService.createRole(newRole) == 1){
            return BaseRespVo.ok(newRole);
        }
        return BaseRespVo.invalidData("角色创建失败！");
    }

    /**
     * @description: 角色授权管理
     * @parameter: [rolePermissions]
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/25 22:57
     */
    // @RequestMapping("/permissions")
    // public BaseRespVo adminRolePermissions(@RequestBody MarketRolesSetPermissionsBo rolePermissions){
    //     if(adminRoleService.updateRolePermissions()==1){
    //         return BaseRespVo.ok(null);
    //     }
    //     return BaseRespVo.invalidData("授权失败！");
    // }

}
