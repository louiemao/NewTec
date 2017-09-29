package com.sws.newtec.modules.security.service.impl;

import com.sws.newtec.modules.security.dao.RoleDao;
import com.sws.newtec.modules.security.entity.Role;
import com.sws.newtec.modules.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午3:24:16
 * 
 * @author andy
 * @version 2.2 UserService 的实现
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role load(String id) {
		return roleDao.load(id);
	}

	@Override
	public Role get(String id) {
		return roleDao.get(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void persist(Role entity) {
		roleDao.persist(entity);
	}

	@Override
	public String save(Role entity) {
		return roleDao.save(entity);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		roleDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
	}

	@Override
	public void flush() {
		roleDao.flush();
	}

}
