package com.cskaoyan.service;

import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.MarketAdmin;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 08:36
 */

public interface AdminAuthService {

//    获得管理员对应的角色和权限
    InfoData getInfo(MarketAdmin marketAdmin);
}
