package com.sws.newtec.modules.security.service.impl;

import com.sws.newtec.modules.security.dao.UserDao;
import com.sws.newtec.modules.security.entity.MenuInfo;
import com.sws.newtec.modules.security.entity.Role;
import com.sws.newtec.modules.security.entity.User;
import com.sws.newtec.modules.security.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 创建时间：2015-2-6 下午3:24:16
 *
 * @author andy
 * @version 2.2 UserService 的实现
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User load(String id) {
        return userDao.load(id);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void persist(User entity) {
        userDao.persist(entity);
    }

    @Override
    public String save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public void saveOrUpdate(User entity) {
        userDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void flush() {
        userDao.flush();
    }

    @Override
    public User getByAccount(String account) {
        return userDao.getByAccount(account);
    }

    @Override
    public User getCurrentUser() {
        final String currentUserId = (String) SecurityUtils.getSubject().getPrincipal();
        if (currentUserId != null) {
            return get(currentUserId);
        } else {
            return null;
        }
    }

    @Override
    public void createUser(String username, String email, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRegisterTime(new Date());
        user.setUsername(username);
        user.setEmail(email);
        //user.setPassword( new Sha256Hash(password).toHex() );
        user.setPassword(password);

        //设置为普通角色
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setId("b432d31d-ebd2-4e91-9184-1b3769e6686c");
        roles.add(role);
        //user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

//    @Override
//    public List<Menu> getMenuInfosByUsername(String username) {
//        User user = userDao.getByAccount(username);
//        if (user == null) {
//            return null;
//        } else {
//            return user.findMenuInfos();
//        }
//    }
}
