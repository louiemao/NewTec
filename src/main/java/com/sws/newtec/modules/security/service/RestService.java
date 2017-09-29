package com.sws.newtec.modules.security.service;

import com.sws.newtec.modules.security.dto.CPUser;
import com.sws.newtec.modules.security.dto.Menu;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.List;

/**
 * Created by Louie on 2015/8/11.
 */
public interface RestService {
    CPUser login(String username,String password);
    SimpleAuthorizationInfo getAuthorizationInfo(String userid);
    List<Menu> getMenuInfos(String userid);
}
