package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketRegion;
import lombok.Data;

import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-27 23:45:43
 * @version:
 * @Description:
 */
@Data
public class MarketRegionInfoVO<T> {
    private Integer id;

    private String name;

    private Byte type;

    private Integer code;

    private List<T> children;

    public static MarketRegionInfoVO changeToVO(MarketRegionVO marketRegionVO){
        MarketRegionInfoVO marketRegionInfoVO = new MarketRegionInfoVO();
        marketRegionInfoVO.setId(marketRegionVO.getId());
        marketRegionInfoVO.setName(marketRegionVO.getName());
        marketRegionInfoVO.setType(marketRegionVO.getType());
        marketRegionInfoVO.setCode(marketRegionVO.getCode());
        return marketRegionInfoVO;
    }
}
