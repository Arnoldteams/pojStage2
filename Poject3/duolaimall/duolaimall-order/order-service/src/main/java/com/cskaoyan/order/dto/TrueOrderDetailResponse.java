package com.cskaoyan.order.dto;

import com.cskaoyan.mall.commons.result.AbstractResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年07月11日 10:06
 */
@Data
public class TrueOrderDetailResponse extends AbstractResponse {

    private String username;

    private BigDecimal orderTotal;

    private Long userId;

    private String tel;

    private String streetName;

    private Integer orderStatus;

    private List<OrderItemDto> goodsList;
}
