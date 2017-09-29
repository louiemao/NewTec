package com.sws.newtec.modules.security.service;

import com.sws.newtec.modules.security.entity.Permission;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午3:18:57
 * 
 * @author andy
 * @version 2.2
 *  接口
 */

public interface PermissionService {
	Permission load(String id);

	Permission get(String id);

	List<Permission> findAll();

	void persist(Permission entity);

	String save(Permission entity);

	void saveOrUpdate(Permission entity);

	void delete(String id);

	void flush();
	
}
