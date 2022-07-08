package com.cskaoyan.service;

import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.bo.AdminProfilePasswordBO;
import com.cskaoyan.mapper.MarketAdminMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 修改密码
 *
 * @author sprinkle
 * @since 2022/06/30 21:14
 */

@Service
public class AdminProfileServiceImpl implements AdminProfileService{

    @Autowired
    MarketAdminMapper marketAdminMapper;

    @Override
    public String updatePassword(AdminProfilePasswordBO admin) {
        MarketAdmin primaryPrincipal = (MarketAdmin) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        Integer id = primaryPrincipal.getId();
        MarketAdmin marketAdmin1 = marketAdminMapper.selectByPrimaryKey(id);
        String password = marketAdmin1.getPassword();
        if(!password.equals(admin.getOldPassword())){
            return null;
        }

        Date date = new Date();

        MarketAdmin marketAdmin = new MarketAdmin();

        marketAdmin.setPassword(admin.getNewPassword());
        marketAdmin.setId(primaryPrincipal.getId());
        marketAdmin.setUpdateTime(date);

        marketAdminMapper.updateByPrimaryKeySelective(marketAdmin);

        return "yeah";
    }
}
