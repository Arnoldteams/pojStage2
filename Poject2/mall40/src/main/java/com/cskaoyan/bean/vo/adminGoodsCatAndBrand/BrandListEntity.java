package com.cskaoyan.bean.vo.adminGoodsCatAndBrand;

import java.util.Date;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月26日 23:49
 */
public class BrandListEntity {
    /**
     * label : MUJI制造商
     * value : 1001000
     */
    private String label;

    private int value;

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}
