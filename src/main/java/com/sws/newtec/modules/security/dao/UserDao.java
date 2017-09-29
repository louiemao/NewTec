package com.sws.newtec.modules.security.dao;

import com.sws.newtec.modules.security.entity.MenuInfo;
import com.sws.newtec.modules.security.entity.User;

/**
 * 创建时间：2015-2-6 下午2:43:50
 *
 * @author andy
 * @version 2.2
 *          <p/>
 *          用户Dao接口
 */

public interface UserDao extends GenericDao<User, String> {
    User getByAccount(String account);
    User getByUsernameAndPassword(String username,String password);
    void updateUser(User user);
}
