package com.cskaoyan.bean;

import lombok.Data;

/**
 * @author stone
 * @date 2022/06/25 11:09
 */
@Data
public class BasePageInfo {
    Integer page;
	Integer limit;
	String sort;
	String order;
	
}
