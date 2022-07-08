package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketRegion;
import lombok.Data;

import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-27 20:07:57
 * @version:
 * @Description:
 */

@Data
public class MarketRegionVO<T> {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    private List<T> children;


    public static MarketRegionVO changeToVO(MarketRegion marketRegion){
        MarketRegionVO<Object> marketRegionVO = new MarketRegionVO<>();
        marketRegionVO.setId(marketRegion.getId());
        marketRegionVO.setPid(marketRegion.getPid());
        marketRegionVO.setName(marketRegion.getName());
        marketRegionVO.setType(marketRegion.getType());
        marketRegionVO.setCode(marketRegion.getCode());
        return marketRegionVO;
    }
}
