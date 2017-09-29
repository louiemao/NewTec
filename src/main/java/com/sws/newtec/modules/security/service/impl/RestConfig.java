package com.sws.newtec.modules.security.service.impl;

/**
 * Created by Louie on 2015/8/24.
 */
public class RestConfig {

    private String URL_BASE;

    public void setURL_BASE(String url_base){
        this.URL_BASE=url_base;
    }

    public String getURL_BASE(){
        return this.URL_BASE;
    }

    public String getURL_LOGIN(){
        return getURL_BASE()+"/CPServiceRelease/Service/Imp/ExternalService/MobileAppLogicService.svc/rest/login";
    }

    public String getURL_GET_MENUS(String userCd){
        return getURL_BASE()+"/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/{USERCD}/xptcs/menus".replace("{USERCD}", userCd);
    }

    public String getURL_GET_ROLES(String userCd){
        return getURL_BASE()+ "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/roles".replace("{USERCD}", userCd);
    }

    public String getURL_GET_PERMISSIONS(String userCd){
        return getURL_BASE()+ "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/operations".replace("{USERCD}", userCd);
    }
}
