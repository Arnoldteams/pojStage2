package com.cskaoyan.configuration.realm;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.vo.DashBoardVO;
import com.cskaoyan.mapper.MarketAdminMapper;
import com.cskaoyan.mapper.MarketPermissionMapper;
import com.cskaoyan.mapper.MarketRoleMapper;
import com.cskaoyan.mapper.MarketUserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Realm做的事情就是提供信息 → 认证信息、授权信息
 * @author stone
 * @date 2022/06/28 11:14
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    MarketAdminMapper marketAdminMapper;

    @Autowired
    MarketUserMapper marketUserMapper;

    @Autowired
    MarketRoleMapper marketRoleMapper;

    @Autowired
    MarketPermissionMapper marketPermissionMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

// 通常根据AuthenticationToken中传入的用户名信息查询出该用户在数据库中的信息
        String type = ((MarketToken) authenticationToken).getType();
        String username = (String) authenticationToken.getPrincipal();

//        获得认证时间和认证ip
        Date date = new Date();
        String remoteHost = request.getRemoteHost();
        if ("admin".equals(type)) {
            MarketAdminExample example = new MarketAdminExample();
            example.createCriteria().andUsernameEqualTo(username);
            List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(example);
            if (marketAdmins.size() == 1) {
                //说明数据库中有一条对应的信息
                MarketAdmin marketAdmin = marketAdmins.get(0);
                marketAdmin.setLastLoginTime(date);
                marketAdmin.setLastLoginIp(remoteHost);

                marketAdminMapper.updateByPrimaryKeySelective(marketAdmin);

                // 构造认证信息时，可以放入你需要的用户信息，而你放入的用户信息，可以作为Principals
                // 放入这个信息，是为了取出这个信息
                // 第二个参数credentials，是真实（正确）的密码，会和AuthenticationToken中的password做比较
                return new SimpleAuthenticationInfo(marketAdmin,marketAdmin.getPassword(),getName());
            }
        } else if ("wx".equals(type)) {
            //查询user表中的信息
            MarketUserExample marketUserExample = new MarketUserExample();
            marketUserExample.createCriteria().andUsernameEqualTo(username);
            List<MarketUser> marketUsers = marketUserMapper.selectByExample(marketUserExample);
            if (marketUsers.size() == 1) {


                //说明数据库中有一条对应的信息
                MarketUser marketUser = marketUsers.get(0);
                marketUser.setLastLoginTime(date);
                marketUser.setLastLoginIp(remoteHost);

                marketUserMapper.updateByPrimaryKeySelective(marketUser);

                // 构造认证信息时，可以放入你需要的用户信息，而你放入的用户信息，可以作为Principals
                // 放入这个信息，是为了取出这个信息
                // 第二个参数credentials，是真实（正确）的密码，会和AuthenticationToken中的password做比较
                return new SimpleAuthenticationInfo(marketUser,marketUser.getPassword(),getName());
            }
        }

        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 要先获得Principal信息
        MarketAdmin principal = (MarketAdmin) principalCollection.getPrimaryPrincipal();
        // 根据用户信息查询出对应的权限列表
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        MarketAdminExample.Criteria adminExampleCriteria = marketAdminExample.createCriteria();
        adminExampleCriteria.andUsernameEqualTo(principal.getUsername());
        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);

        MarketAdmin marketAdmin = marketAdmins.get(0);

        Integer[] roleIds = marketAdmin.getRoleIds();
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("dashboard");
        for (Integer roleId : roleIds) {
            List<String> permissionApiById = marketPermissionMapper.selectPermissionApiById(roleId);
            permissionList.addAll(permissionApiById);
        }


        // mybatis来查询
//        List<String> permissions = permissionList;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }
}
