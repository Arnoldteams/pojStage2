package com.cskaoyan.bean.vo;

import java.io.Serializable;

/**
 *
 * @since 2022/06/26 14:24
 * @author Gzy
 */


public class PermissionGrandChildVo implements Serializable {
    private static final long serialVersionUID = 2L;

    /**
     * id : admin:category:list
     * label : 查询
     * api : GET /admin/category/list
     */
    private String id;
    private String label;
    private String api;

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getApi() {
        return api;
    }

}
