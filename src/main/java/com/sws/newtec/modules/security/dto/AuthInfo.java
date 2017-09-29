package com.sws.newtec.modules.security.dto;

/**
 * Created by Louie on 2015/7/23.
 */
public class AuthInfo {
    private AuthcInfo authc=new AuthcInfo();
    private AuthzInfo authz=new AuthzInfo();

    public AuthcInfo getAuthc() {
        return authc;
    }

    public void setAuthc(AuthcInfo authc) {
        this.authc = authc;
    }

    public AuthzInfo getAuthz() {
        return authz;
    }

    public void setAuthz(AuthzInfo authz) {
        this.authz = authz;
    }
}
