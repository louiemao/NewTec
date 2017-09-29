package com.sws.newtec.modules.security.dto;


/**
 * Created by Louie on 2015/7/23.
 */
public class AuthcInfo {
    private CPUser principal=new CPUser();
    private Token credentials=new Token();

    public CPUser getPrincipal() {
        return principal;
    }

    public void setPrincipal(CPUser principal) {
        this.principal = principal;
    }

    public Token getCredentials() {
        return credentials;
    }

    public void setCredentials(Token credentials) {
        this.credentials = credentials;
    }
}
