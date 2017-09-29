package com.sws.newtec.modules.security.service;

import com.sws.newtec.modules.security.entity.Role;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午3:18:57
 * 
 * @author andy
 * @version 2.2
 *  RoleService接口
 */

public interface RoleService {
	Role load(String id);

	Role get(String id);

	List<Role> findAll();

	void persist(Role entity);

	String save(Role entity);

	void saveOrUpdate(Role entity);

	void delete(String id);

	void flush();
}
