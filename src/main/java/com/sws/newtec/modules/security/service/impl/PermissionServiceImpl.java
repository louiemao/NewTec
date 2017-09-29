package com.sws.newtec.modules.security.service.impl;

import com.sws.newtec.modules.security.dao.PermissionDao;
import com.sws.newtec.modules.security.entity.Permission;
import com.sws.newtec.modules.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午3:24:16
 * 
 * @author andy
 * @version 2.2 
 * PermissionService 的实现
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Permission load(String id) {
		return permissionDao.load(id);
	}

	@Override
	public Permission get(String id) {
		return permissionDao.get(id);
	}

	@Override
	public List<Permission> findAll() {
		return permissionDao.findAll();
	}

	@Override
	public void persist(Permission entity) {
		permissionDao.persist(entity);
	}

	@Override
	public String save(Permission entity) {
		return permissionDao.save(entity);
	}

	@Override
	public void saveOrUpdate(Permission entity) {
		permissionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(String id) {
		permissionDao.delete(id);
	}

	@Override
	public void flush() {
		permissionDao.flush();
	}

}
