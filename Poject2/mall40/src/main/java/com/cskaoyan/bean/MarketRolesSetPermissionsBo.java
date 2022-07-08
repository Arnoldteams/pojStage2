package com.cskaoyan.bean;

import java.util.List;

/**
 *
 * @since 2022/06/25 22:53
 * @author Gzy
 */


public class MarketRolesSetPermissionsBo {

    /**
     * roleId : 9
     * permissions : ["index:permission:write","index:permission:read"]
     */
    private Integer roleId;
    private List<String> permissions;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
