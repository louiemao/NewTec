package com.sws.newtec.modules.security.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Louie on 2015/7/23.
 */
public class AuthzInfo {
    private Set<String> roles=new HashSet<String>();
    private Set<String> permissions=new HashSet<String>();

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}
