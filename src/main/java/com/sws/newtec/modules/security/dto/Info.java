package com.sws.newtec.modules.security.dto;

/**
 * Created by Louie on 2015/7/23.
 */
public class Info {
    private AuthInfo info=new AuthInfo();

    public AuthInfo getInfo() {
        return info;
    }

    public void setInfo(AuthInfo info) {
        this.info = info;
    }
}
