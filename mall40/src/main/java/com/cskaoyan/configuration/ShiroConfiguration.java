package com.cskaoyan.configuration;

import com.cskaoyan.configuration.realm.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * @author stone
 * @date 2022/06/28 11:06
 */
@Configuration
public class ShiroConfiguration {

    /**
     * ShiroFilter → SecurityManager
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 通常anon ← authc ← perms
        // 通常anon写在最前面

        //保证map中的值是有序的 Linked
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // map中key    → 请求url
        // map中value  → filter名称

//        后台匿名请求
        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/auth/logout", "anon");
        filterChainDefinitionMap.put("/admin/auth/info", "anon");

        filterChainDefinitionMap.put("/wx/auth/login", "anon");
        filterChainDefinitionMap.put("/wx/auth/logout", "anon");
        filterChainDefinitionMap.put("/wx/goods/index", "anon");
        filterChainDefinitionMap.put("/wx/search/index", "anon");
        filterChainDefinitionMap.put("/wx/brand/list", "anon");

        filterChainDefinitionMap.put("/wx/**", "anon");
        filterChainDefinitionMap.put("/admin/auth/noAuthc", "anon");
        filterChainDefinitionMap.put("/wx/cart/goodscount", "anon");

        filterChainDefinitionMap.put("/wx/order/**", "anon");

        /*在开发的时候打开，测试的时候关闭*/

//        filterChainDefinitionMap.put("/admin/profile/nnotice", "anon");
        filterChainDefinitionMap.put("/admin/**", "authc");
        /*在开发的时候关闭，测试的时候打开*/
//        filterChainDefinitionMap.put("/wx/**", "authc");



        // 含义就是访问/admin/user/list这个请求需要的权限是aaa
        // 但是通常我们不这样写，因为增加权限通常指的url → 对handler方法做访问控制
        // 耦合 handler方法和权限做耦合
        // filterChainDefinitionMap.put("/admin/user/list", "perms[aaa]");

        // /admin/auth/login → anon
        // /admin/user/list → authc

        //如果访问某个请求的时候，该请求对应的filter是authc，如果没有通过这个filter则说明没有权限
        // shiro会给你做重定向 → 默认的重定向的地址 /login.jsp
        // 如果想要修改重定向的地址，可以使用方法来修改
        shiroFilterFactoryBean.setLoginUrl("/admin/auth/noAuthc");
//        shiroFilterFactoryBean.setLoginUrl("/admin/profile/nnotice");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager →
     * Authenticator 可有可无的
     * Authorizer 可有可无的
     * Realms
     * SessionManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm realm,
                                                     DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 给SecurityManager、默认的认证器、默认的授权器都设置了Realms
        securityManager.setRealms(Arrays.asList(realm));

        //如果要使用自定义的认证器和授权器，需要你单独使用set方法，还需给自定义的认证器和授权器单独提供Realms
        //securityManager.setAuthenticator();
        //securityManager.setAuthorizer();

        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * Realm extends AuthorizingRealm
     * 也可以直接增加@Component注册为容器中的组件
     */
    /*@Bean
    public AuthorizingRealm realm() {
        return new CustomRealm();
    }*/

    /**
     * SessionManager
     * 选择Shiro提供的，看包
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        //return new DefaultWebSessionManager();
        return new MarketSessionManager();
    }

    /**
     * 注册Advisor组件，用于授权映射
     * @param securityManager
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @author xyg2597@163.com
     * @since 2022/06/29 8:42
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
