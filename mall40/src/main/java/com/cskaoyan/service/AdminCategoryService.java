package com.cskaoyan.service;

import com.cskaoyan.bean.AdminCategoryOne;
import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryChildren;

import java.util.List;

public interface AdminCategoryService {

    /**
     * @author: sprinkle
     * @createTime: 2022/06/27 20:14:11
     * @description:admin/category/list
     * @return: java.util.List<com.cskaoyan.bean.MarketCategory>
     */
    List<MarketCategoryChildren> getAllCategory();

    /**
     * @author: sprinkle
     * @createTime: 2022/06/27 20:59:57
     * @description:admin/category/l1
     * @return: java.util.List<com.cskaoyan.bean.AdminCategoryOne>
     */
    List<AdminCategoryOne> getL1Category();

    /**
     * @author: sprinkle
     * @createTime: 2022/06/27 21:17:57
     * @description:admin/category/l1
     * @return: com.cskaoyan.bean.MarketCategory
     */
    MarketCategory createOneCategory(MarketCategory marketCategory);

    /**
     * @author: sprinkle
     * @createTime: 2022/06/28 13:54:48
     * @description:admin/category/update
     * @return: com.cskaoyan.bean.MarketCategory
     */
    void updateOneCategory(MarketCategory marketCategory);

    /**
     * @author: sprinkle
     * @createTime: 2022/06/28 14:30:31
     * @description:admin/category/delete
     * @return: com.cskaoyan.bean.MarketCategory
     */
    void deleteOneCategory(MarketCategory marketCategory);
}
