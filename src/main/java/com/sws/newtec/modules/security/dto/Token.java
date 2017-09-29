package com.sws.newtec.modules.security.dto;

/**
 * Created by Louie on 2015/7/23.
 */
public class Token {
    private String principal;
    private String credentials;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}
