package com.sws.newtec.modules.security.controller;

import com.sws.newtec.modules.security.dto.CPUser;
import com.sws.newtec.modules.security.dto.Info;
import com.sws.newtec.modules.security.dto.Menu;
import com.sws.newtec.modules.security.dto.TokenInfo;

import com.sws.newtec.modules.security.entity.User;
import com.sws.newtec.modules.security.service.RestService;
import com.sws.newtec.modules.security.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Louie on 2015/7/15.
 */
@Controller
public class RestUserController {
    private final static Logger logger = LoggerFactory.getLogger(RestUserController.class);
    @Autowired
    UserService userService;
    @Autowired
    RestService restService;

    @RequestMapping(value = "/restUser/queryUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getUsers() {
        logger.info("查找所有的用户信息");
        return userService.findAll();
    }

    @RequestMapping(value = "/restUser/getUserById/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserById(@PathVariable("id") String id) {
        logger.info("查找用户信息:" + id);
        return userService.get(id);
    }

    @RequestMapping(value = "/restUser/createUser", method = RequestMethod.POST)
    public
    @ResponseBody
    void createUser(@RequestBody User user) {
        logger.info("创建用户:" + user.toString());
        userService.createUser(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "/restUser/updateUser", method = RequestMethod.POST)
    public
    @ResponseBody
    void updateUser(@RequestBody User user) {
        logger.info("修改用户信息:" + user.toString());
        userService.updateUser(user);
    }

    @RequestMapping(value = "/restUser/removeUserById/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void removeUserById(@PathVariable("id") String id) {
        logger.info("删除用户信息：" + id);
        userService.delete(id);
    }

//    @RequestMapping(value = "/restUser/getMenuInfosByUsername/{username}", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    List<Menu> getMenuInfosByUsername(@PathVariable("username") String username) {
//        if (username == null) {
//            username = "guest";
//        }
//        logger.info("查询用户可用菜单：" + username);
//        return userService.getMenuInfosByUsername(username);
//    }

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public
    @ResponseBody
    Info authenticate(@RequestBody TokenInfo token) {
        logger.info(token.getToken().getPrincipal());
        // UsernamePasswordToken token1 = new UsernamePasswordToken(token.getUsername(), token.getPassword(),token.isRememberMe());
        UsernamePasswordToken token1 = new UsernamePasswordToken(token.getToken().getPrincipal(), token.getToken().getCredentials());
        try {
            logger.info(token1.getUsername());
            SecurityUtils.getSubject().login(token1);

            CPUser user = restService.login(token1.getUsername(), String.valueOf(token1.getPassword()));
            if (user != null) {
                //添加角色及权限信息
                Info info = new Info();
                info.getInfo().getAuthc().setPrincipal(user);
                info.getInfo().getAuthc().setCredentials(token.getToken());

                SimpleAuthorizationInfo sinfo = restService.getAuthorizationInfo(user.userCd);
                info.getInfo().getAuthz().setRoles(sinfo.getRoles());
                info.getInfo().getAuthz().setPermissions(sinfo.getStringPermissions());
                return info;
            } else {
                return null;
            }
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/api/logout")
    public
    @ResponseBody
    void logout() {
        logger.info("logout running...");
        SecurityUtils.getSubject().logout();
    }

    @RequestMapping("/cp/getMenuInfos/{userCd}")
    public
    @ResponseBody
    List<Menu> getMenuInfos(@PathVariable("userCd") String userCd) {
        return restService.getMenuInfos(userCd);
    }

    @RequestMapping("/test")
    public
    @ResponseBody
    List<Menu> test() {
        return restService.getMenuInfos("");
    }
}
