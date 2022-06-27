package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.param.BaseParam;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 显示管理员信息、日志信息
 *
 * @author xyg2597@163.com
 * @since 2022/06/26 15:54
 */

@Data
public class AdminListVO<T> {

    private List<T> list;

    private Integer page;
    private Integer limit;
    private Long total;
    private Integer pages;

    public void setBaseParam(PageInfo info){
        setPage(info.getPageNum());
        setLimit(info.getPageSize());
        setTotal(info.getTotal());
        setPages(info.getPages());
    }

}
