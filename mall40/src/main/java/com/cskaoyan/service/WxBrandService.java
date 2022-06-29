package com.cskaoyan.service;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketBrand;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminBrandVO;
/**
 * @author sprinkle
 * @since 2022/06/29 11:30
 */
public interface WxBrandService {

    CommonData<MarketBrand> quaryAllBrand(Integer page, Integer limit);

    MarketBrand queryOneBrand(String id);
}
