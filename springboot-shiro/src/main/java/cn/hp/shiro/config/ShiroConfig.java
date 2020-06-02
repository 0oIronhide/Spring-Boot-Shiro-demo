package cn.hp.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ironhide
 * @create 2020-04-25-16:03
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean对象
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        /*
        *   anon:无需认证就可以访问
            authc:必须认证了才能访问
            user: 必须拥有记住我功能才能用
            perms:拥有对某 个资源的权限才能访间;
            role:拥有某个角色权限才能访问
        * */
        Map<String, String> map = new LinkedHashMap<>();
        //定义请求是否需要权限
        map.put("/user/add", "perms[add]");
        map.put("/user/update", "perms[update]");
        bean.setFilterChainDefinitionMap(map);
        //定义去登录页面的请求
        bean.setLoginUrl("/toLoginPage");
        //定义未授权的请求
        bean.setUnauthorizedUrl("/unAuthorized");
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") UserRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    //Realm自定义对象
    @Bean
    public UserRealm getRealm() {
        return new UserRealm();
    }

    //shiro与thymeleaf的整合
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
