package org.shiro.demo01.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     然后编写shiro的配置类ShiroConfig，其中声明三个对象：
     1. =>  realm安全实体数据源：用我们自定义的UserRealm类来创建
     2. =>  DefaultWebSecurityManager默认安全管理器：该对象需要关联realm对象，在方法参数中传入realm对象的参数，
     用@Qualifier指定需要的realm实现类UserRealm方法名即可
     3. =>  ShiroFilterFactoryBeanshiro过滤工厂对象：该对象需要关联SecurityManager对象，同样在参数中传入该对象的参数
     用@Qualifier指定需要的实现类方法名
     * */


    //创建realm对象,自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;

        //ShiroFilterFactoryBean

    }

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置的过滤器
        /*
            anon:  无需认证就可以访问
            authc: 必须认证了才能访问
            user:  必须拥有记住我功能才能使用
            perms：拥有对某个资源的权限才能访问
            role:  拥有某个角色权限才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        //设置授权,只有user:add权限的才能请求/user/add
        filterMap.put("/user/add","perms[user:add]");
        //设置授权,只有user:update权限的才能请求/user/update
        filterMap.put("/user/update","perms[user:update]");
        //filterMap.put("/user/*", "authc");支持通配符
        bean.setFilterChainDefinitionMap(filterMap);//参数为map类型
        //设置登录的请求
        bean.setLoginUrl("/login");
        //设置未授权页面的请求
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }

    //配式shiro整合thymeleaf
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
