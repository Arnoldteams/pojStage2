package com.cskaoyan.service;


import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.MarketAdminExample;
import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.mapper.MarketAdminMapper;
import com.cskaoyan.mapper.MarketPermissionMapper;
import com.cskaoyan.mapper.MarketRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xyg2597@163.com
 * @since 2022/06/29 08:36
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    @Autowired
    MarketAdminMapper marketAdminMapper;

    @Autowired
    MarketPermissionMapper marketPermissionMapper;

    @Autowired
    MarketRoleMapper marketRoleMapper;

    /**
     * 获得权限信息 
     * @param marketAdmin 
     * @return com.cskaoyan.bean.InfoData
     * @author xyg2597@163.com
     * @since 2022/06/29 11:11
     */
    @Override
    public InfoData getInfo(MarketAdmin marketAdmin) {

        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria criteria = marketAdminExample.createCriteria();
        criteria.andUsernameEqualTo(marketAdmin.getUsername());

        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);

        MarketAdmin queryMarketAdmin = marketAdmins.get(0);

        Integer[] roleIds = queryMarketAdmin.getRoleIds();

        ArrayList<String> adminPermissionList = new ArrayList<>();
        ArrayList<String> adminRoleList = new ArrayList<>();

        if (roleIds.length != 0) {
            for (Integer roleId : roleIds) {
                List<String> permissionApiById = marketPermissionMapper.selectPermissionApiById(roleId);

                for (int i = 0; i < permissionApiById.size(); i++) {
                    String permission = permissionApiById.get(i);
                    if ("*".equals(permission) ){
                        adminPermissionList.add(permission);
                    }
                    String permissionApi = marketPermissionMapper.queryApiByPermission(permission);
                    adminPermissionList.add(permissionApi);

                }

                MarketRole marketRole = marketRoleMapper.selectByPrimaryKey(roleId);
                adminRoleList.add(marketRole.getName());
            }
        }

        InfoData infoData = new InfoData();

        infoData.setAvatar(marketAdmin.getAvatar());
        infoData.setName(marketAdmin.getUsername());
        infoData.setPerms(adminPermissionList);
        infoData.setRoles(adminRoleList);
        return infoData;
    }
}
