package com.cskaoyan.bean.validParam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月30日 22:02
 */
@Data
@Validated
public class ValidParamAdminGoodsCreate {

    @DecimalMin("0")
    private String goodsSn;

    @DecimalMin("0")
    private BigDecimal retailPrice;

    @DecimalMin("0")
    private String name;

    @DecimalMin("0")
    private BigDecimal price;

    @DecimalMin("0")
    private Integer number;

    @Length(min = 1, max = 1)
    private String unit;

}
