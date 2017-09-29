package com.sws.newtec.modules.security.service;

import com.sws.newtec.modules.security.entity.MenuInfo;
import com.sws.newtec.modules.security.entity.User;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午3:18:57
 * 
 * @author andy
 * @version 2.2
 *  userService接口
 */

public interface UserService {
	User load(String id);

	User get(String id);

	List<User> findAll();

	void persist(User entity);

	String save(User entity);

	void saveOrUpdate(User entity);

	void delete(String id);

	void flush();

	User getByAccount(String account);

	User getCurrentUser();

	void createUser(String username, String email, String password);

	User getByUsernameAndPassword(String username,String password);

	void updateUser(User user);

	//List<Menu> getMenuInfosByUsername(String username);
}
