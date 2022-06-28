package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketRegion;
import lombok.Data;

import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-27 23:48:15
 * @version:
 * @Description:
 */
@Data
public class MarketRegionInfoNoChildVO {
    private Integer id;

    private String name;

    private Byte type;

    private Integer code;

    public static MarketRegionInfoNoChildVO changeToVO(MarketRegionVO marketRegion){
        MarketRegionInfoNoChildVO marketRegionInfoNoChildVO = new MarketRegionInfoNoChildVO();
        marketRegionInfoNoChildVO.setId(marketRegion.getId());
        marketRegionInfoNoChildVO.setName(marketRegion.getName());
        marketRegionInfoNoChildVO.setType(marketRegion.getType());
        marketRegionInfoNoChildVO.setCode(marketRegion.getCode());
        return marketRegionInfoNoChildVO;
    }
}
