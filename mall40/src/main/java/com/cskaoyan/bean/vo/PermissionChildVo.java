package com.cskaoyan.bean.vo;

import java.util.List;

/**
 *
 * @since 2022/06/26 14:23
 * @author Gzy
 */


public class PermissionChildVo {
    /**
     * id : 类目管理
     * label : 类目管理
     */
    private Integer key;
    private String id;
    private String label;
    private List<PermissionGrandChildVo> children;

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public List<PermissionGrandChildVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionGrandChildVo> children) {
        this.children = children;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
