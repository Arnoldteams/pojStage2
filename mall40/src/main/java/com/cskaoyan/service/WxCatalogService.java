package com.cskaoyan.service;

import com.cskaoyan.bean.vo.WxCatalogCurrentVO;
import com.cskaoyan.bean.vo.WxCatalogIndexVO;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-28 22:53:53
 * @version:
 * @Description:
 */
public interface WxCatalogService {
    WxCatalogIndexVO showCatalogIndex();

    /**
     * @author sprinkle
     * @since 2022/06/29 15:50
     */

    WxCatalogCurrentVO queryOneCatalog(String id);
}
