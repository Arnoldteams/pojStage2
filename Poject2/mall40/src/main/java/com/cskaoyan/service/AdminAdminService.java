package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.bo.AdminCreateBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminCreateVO;
import com.cskaoyan.bean.vo.AdminListVO;

import java.util.List;

/**
 * 管理员管理模块接口
 * @author xyg2597@163.com
 * @since 2022/06/26 15:44
 */
public interface AdminAdminService {

//    获得管理员列表
    AdminListVO adminList(BaseParam info, String username);

//    创建管理员
    AdminCreateVO adminCreate(AdminCreateBO adminCreateBO);

//    修改管理员信息
    String adminUpdate(MarketAdmin marketAdmin);

//    删除指定管理员
    void adminDelete(MarketAdmin marketAdmin);
}
