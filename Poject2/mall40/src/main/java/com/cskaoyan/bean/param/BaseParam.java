package com.cskaoyan.bean.param;

import lombok.Data;

// import javax.validation.constraints.Min;

/**
 * @author stone
 * @date 2022/01/06 16:37
 */
@Data
public class BaseParam {

    Integer page;
    Integer limit;
    String sort;
    String order;
}
