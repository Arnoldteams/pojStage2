package com.cskaoyan.bean.vo;

import java.util.List;

/**
 *
 * @since 2022/06/26 13:00
 * @author Gzy
 */


public class MarketSystemPermissionsVo {

    private List<PermissionsVo> systemPermissions;

    private List<String> assignedPermissions;

    public List<PermissionsVo> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(List<PermissionsVo> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }


    public void setAssignedPermissions(List<String> assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }

    public List<String> getAssignedPermissions() {
        return assignedPermissions;
    }
}
