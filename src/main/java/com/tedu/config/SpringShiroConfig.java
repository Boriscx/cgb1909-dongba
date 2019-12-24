package com.tedu.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SpringShiroConfig {

    /**
     * <p>shiro框架的和兴安全管理器对象</p>
     * <p>-@Bean注解描述的的方法,器返回值会交给spring管理,spring存储</p>
     *
     * @return 安全管理器对象
     */
    @Bean
    public SecurityManager shiroSecurityManager(Realm realm
                                                //,CacheManager cacheManager
                                                //,RememberMeManager rememberMeManager
                                                ,SessionManager sessionManager
    ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
//        securityManager.setCacheManager(cacheManager);
//        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 认证路径配置
     *
     * @param securityManager 安全管理器
     * @return 认证路径
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全
        factoryBean.setSecurityManager(securityManager);
        // 设置认证url(哪些资源可以无需认证(anon),哪些需要认证(authc))
        factoryBean.setLoginUrl("/login.html");// 没有登录时返回的页面
//        factoryBean.setUnauthorizedUrl("/");//没有权限时返回的页面
        //设置过滤规则
        Map<String, String> map = new LinkedHashMap<>();
        // 静态资源允许匿名访问,即不需要认证即可访问:"anon
        map.put("/bower_components/**", "anon");
        map.put("/build/**", "anon");
        map.put("/dist/**", "anon");
        map.put("/plugins/**", "anon");
        map.put("/user/doLogin", "anon");
        map.put("/user/doLogout", "logout");
        map.put("/login.html","anon");
        // 其他都要认证资源:"authc"
//        map.put("/**", "authc");
        // 使用rememberMe功能时需要使用user
        map.put("/**", "user");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }
    /**
     * shiro框架的会话管理对象
     * 回话时长配置(Session)
     */
    @Bean
    public SessionManager shiroSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(30 * 60 * 1000);  // 设置会话时长
        return defaultWebSessionManager;
    }



}
