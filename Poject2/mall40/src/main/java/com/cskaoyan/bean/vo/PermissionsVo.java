package com.cskaoyan.bean.vo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @since 2022/06/26 14:21
 * @author Gzy
 */


public class PermissionsVo implements Serializable {
    private static final long serialVersionUID = 2L;


    private  Integer key;
    private String id;
    private String label;
    private List<PermissionChildVo> children;

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

    public List<PermissionChildVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionChildVo> children) {
        this.children = children;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
