package com.sws.newtec.modules.security.dao.impl;

import com.sws.newtec.modules.security.dao.RoleDao;
import com.sws.newtec.modules.security.entity.Role;
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
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Role load(String id) {
		return (Role) this.getCurrentSession().load(Role.class, id);
	}
	
	@Override
	public Role get(String id) {
		return (Role) this.getCurrentSession().get(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		Query query = this.getCurrentSession().createQuery("from Role");
		return query.list();
	}

	@Override
	public void persist(Role entity) {
		this.getCurrentSession().persist(entity);

	}

	@Override
	public String save(Role entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(String id) {
		Role entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();

	}

}
