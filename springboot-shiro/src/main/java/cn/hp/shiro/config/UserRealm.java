package cn.hp.shiro.config;

import cn.hp.shiro.beans.User;
import cn.hp.shiro.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ironhide
 * @create 2020-04-25-16:01
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper mapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进行授权操作...");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getPrincipal();
        //获取用户对应权限
        info.setStringPermissions(mapper.getUserPermission(user.getRoleId()));
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("进行认证操作...");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = mapper.login(userToken.getUsername());
        if (user == null) {
            return null;
        }
        //user传入session中
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("userName", user.getUserName());

        //进行密码认证
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), "");
    }
}
