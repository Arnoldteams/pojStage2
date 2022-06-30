package com.cskaoyan.bean.vo.wxGoodsDetailVo;

import com.cskaoyan.bean.MarketComment;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 22:16
 */
@Data
public class WxGoodsDetailCommentVo {

    private Integer count;

    private List<MarketComment> data;
}
