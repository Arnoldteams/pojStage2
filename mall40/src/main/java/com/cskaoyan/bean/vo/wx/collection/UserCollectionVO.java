package com.cskaoyan.bean.vo.wx.collection;

import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketGoods;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 21:20
 */
@Data
public class UserCollectionVO {

    private Integer id;
    private Integer valueId;
    private Byte type;
    private String name;
    private String brief;
    private BigDecimal retailPrice;

//    封装从收藏表中查询的数据
    public void setCollectionInfo(MarketCollect marketCollect){
        this.setId(marketCollect.getId());
        this.setType(marketCollect.getType());
        this.setValueId(marketCollect.getValueId());
    }

//    封装从商品表中查询的数据
    public void setGoodsInfo(MarketGoods marketGoods){
        this.setName(marketGoods.getName());
        this.setBrief(marketGoods.getBrief());
        this.setRetailPrice(marketGoods.getRetailPrice());
    }

}
