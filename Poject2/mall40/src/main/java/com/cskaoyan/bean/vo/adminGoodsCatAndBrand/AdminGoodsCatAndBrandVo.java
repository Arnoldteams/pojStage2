package com.cskaoyan.bean.vo.adminGoodsCatAndBrand;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月26日 23:32
 */

public class AdminGoodsCatAndBrandVo {


    /**
     * categoryList : [{"children":[{"label":"袜子","value":1008004},{"label":"内衣","value":1010001},{"label":"内裤","value":1010002},{"label":"婴童","value":1011000},{"label":"杂货","value":1012000},{"label":"家居服","value":1013006},{"label":"T恤","value":1015001},{"label":"外衣","value":1020009},{"label":"衬衫","value":1020010},{"label":"丝袜","value":1034000},{"label":"卫衣","value":1035000},{"label":"毛衣","value":1035001},{"label":"裤装","value":1035002},{"label":"1","value":1036012}],"label":"服装","value":1010000},{"children":[{"label":"毛巾","value":1008001},{"label":"日用清洁","value":1009000},{"label":"美妆","value":1013002},{"label":"护发","value":1013003},{"label":"香薰","value":1013004},{"label":"面部护理","value":1020001},{"label":"用具","value":1020002}],"label":"洗护","value":1013001},{"children":[{"label":"夏日甜心","value":1018000},{"label":"礼品卡","value":1025000}],"label":"志趣","value":1019000}]
     * brandList : [{"label":"MUJI制造商","value":1001000},{"label":"内野制造商","value":1001002}]
     */

    private List<CategoryListEntity> categoryList;

    private List<BrandListEntity> brandList;

    public void setCategoryList(List<CategoryListEntity> categoryList) {
        this.categoryList = categoryList;
    }

    public void setBrandList(List<BrandListEntity> brandList) {
        this.brandList = brandList;
    }

    public List<CategoryListEntity> getCategoryList() {
        return categoryList;
    }

    public List<BrandListEntity> getBrandList() {
        return brandList;
    }




}
