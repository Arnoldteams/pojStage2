package com.cskaoyan.bean.validParam;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月30日 21:42
 */

@Data
@Validated
public class ValidParam {

    @DecimalMin("0")
    private Integer goodsSn;

    private String name;

    @DecimalMin("0")
    private Integer goodsId;


}
