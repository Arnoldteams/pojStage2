package com.cskaoyan.bean.vo.wx;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 专题列表返回信息
 *
 * @author xyg2597@163.com
 * @since 2022/06/29 16:58
 */
@Data
public class WxListVO<T> {
    private List<T> list;

    private Integer page;
    private Integer limit;
    private Long total;
    private Integer pages;

    public void setBaseInfo(PageInfo pageInfo) {
        setPage(pageInfo.getPageNum());
        setLimit(pageInfo.getPageSize());
        setTotal(pageInfo.getTotal());
        setPages(pageInfo.getPageSize());
    }
}
