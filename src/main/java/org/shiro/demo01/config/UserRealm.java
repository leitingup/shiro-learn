package org.shiro.demo01.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.shiro.demo01.pojo.User;
import org.shiro.demo01.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前subject
        Subject subject = SecurityUtils.getSubject();
        //通过subject获取当前user
        String s = JSONObject.toJSON(subject.getPrincipal()).toString();
        User CurrentUser = JSONObject.parseObject(s,User.class);
        //设置当前user的权限(从数据库中读取)
        info.addStringPermission(CurrentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.queryUserByName(token.getUsername());
        if(user==null){
            return null;//只需要return null,就会自动抛出UnknownAccountException异常
        }
        //将用户信息存入session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);
        //密码认证,涉及到安全问题,shiro自动完成
        return new SimpleAuthenticationInfo(user, user.getPwd(), user.getName());
    }
}
