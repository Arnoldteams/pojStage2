package com.cskaoyan.bean.vo.adminGoodsCatAndBrand;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月26日 23:48
 */
public class CategoryListEntity {
    /**
     * children : [{"label":"袜子","value":1008004},{"label":"内衣","value":1010001},{"label":"内裤","value":1010002},{"label":"婴童","value":1011000},{"label":"杂货","value":1012000},{"label":"家居服","value":1013006},{"label":"T恤","value":1015001},{"label":"外衣","value":1020009},{"label":"衬衫","value":1020010},{"label":"丝袜","value":1034000},{"label":"卫衣","value":1035000},{"label":"毛衣","value":1035001},{"label":"裤装","value":1035002},{"label":"1","value":1036012}]
     * label : 服装
     * value : 1010000
     */
    private List<ChildrenEntity> children;
    private String label;
    private int value;

    public void setChildren(List<ChildrenEntity> children) {
        this.children = children;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<ChildrenEntity> getChildren() {
        return children;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }


}
