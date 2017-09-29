package com.sws.newtec.modules.security.dao.impl;

import com.sws.newtec.modules.security.dao.UserDao;
import com.sws.newtec.modules.security.entity.MenuInfo;
import com.sws.newtec.modules.security.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建时间：2015-2-6 下午2:45:14
 *
 * @author andy
 * @version 2.2
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateDao implements UserDao {
    private final static Logger logger= LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User load(String id) {
        return (User) this.getCurrentSession().load(User.class, id);
    }

    @Override
    public User get(String id) {
        return (User) this.getCurrentSession().get(User.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        List<User> users = this.getCurrentSession().createQuery("from User").setCacheable(true).list();
        return users;
    }

    @Override
    public void persist(User entity) {
        this.getCurrentSession().persist(entity);
    }
    @Transactional
    @Override
    public String save(User entity) {
        logger.info("UserDaoImpl.save()");
        logger.info(entity.toString());

        return (String) this.getCurrentSession().save(entity);
    }
    @Transactional
    @Override
    public void saveOrUpdate(User entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }
    @Transactional
    @Override
    public void delete(String id) {
        User entity = this.load(id);
        this.getCurrentSession().delete(entity);
    }

    @Override
    public void flush() {
        this.getCurrentSession().flush();

    }

    public User getByAccount(String account) {
        return (User) getCurrentSession().createQuery("from User u where u.username = :account").setString("account", account).uniqueResult();
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return (User) getCurrentSession()
                .createQuery("from User u where u.username = :username and u.password=:password")
                .setString("username", username)
                .setString("password", password)
                .uniqueResult();
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        getCurrentSession().update(user);
    }


}
