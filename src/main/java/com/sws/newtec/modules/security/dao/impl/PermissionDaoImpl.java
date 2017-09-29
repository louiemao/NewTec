package com.sws.newtec.modules.security.dao.impl;

import com.sws.newtec.modules.security.dao.PermissionDao;
import com.sws.newtec.modules.security.entity.Permission;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午2:45:14
 * 
 * @author andy
 * @version 2.2
 */
@Repository("permissionDao")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Permission load(String id) {
		return (Permission) this.getCurrentSession().load(Permission.class, id);
	}
	
	@Override
	public Permission get(String id) {
		return (Permission) this.getCurrentSession().get(Permission.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findAll() {
		Query query = this.getCurrentSession().createQuery("from Permission ");
		return query.list();
	}

	@Override
	public void persist(Permission entity) {
		this.getCurrentSession().persist(entity);

	}

	@Override
	public String save(Permission entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Permission entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(String id) {
		Permission entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();

	}

}
