package com.cskaoyan.bean.bo;

import java.util.List;

/**
 *
 * @since 2022/06/26 17:38
 * @author Gzy
 */


public class AdminPermissionsBo {
    List<String> permissions;
    Integer roleId;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permisssions) {
        this.permissions = permisssions;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
