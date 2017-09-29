//package com.sws.newtec.modules.security.realm;
//
//import com.sws.newtec.modules.security.entity.Permission;
//import com.sws.newtec.modules.security.entity.Role;
//import com.sws.newtec.modules.security.entity.User;
//import com.sws.newtec.modules.security.service.UserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by Louie on 2015/7/2.
// */
//public class ShiroDbRealm extends AuthorizingRealm {
//    public Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private UserService userService;
//
//    public ShiroDbRealm(){
//        setName("ShiroDbRealm");
//    }
//
//    /**
//     * 权限认证
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
//        String id = (String) super.getAvailablePrincipal(principals);
//        logger.info("认证用户权限："+id);
//        User user = userService.get(id);
//        if (user != null) {
//            //添加角色及权限信息
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            for (Role role : user.getRoles()) {
//                info.addRole(role.getName());
//                for (Permission permission : role.getPermissions()) {
//                    info.addStringPermission(permission.getName());
//                }
//            }
//            return info;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * 登录认证;
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(
//            AuthenticationToken authcToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        logger.info("登录认证："+token.getUsername());
//        User user = userService.getByUsernameAndPassword(token.getUsername(), String.valueOf(token.getPassword()));
//        if( user != null ) {
//            return new SimpleAuthenticationInfo(user.getId(), token.getPassword(), getName());
//        } else {
//            return null;
//        }
//    }
//
//}
