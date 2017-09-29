package com.sws.newtec.modules.security.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sws.newtec.modules.security.dto.CPMenuInfo;
import com.sws.newtec.modules.security.dto.CPUser;
import com.sws.newtec.modules.security.dto.Menu;
import com.sws.newtec.modules.security.service.RestService;
import com.sws.newtec.modules.security.util.Md5Util;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

/**
 * Created by Louie on 2015/8/11.
 */
@Service("restService")
public class RestServiceImpl implements RestService {
    private final static Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

    @Autowired
    private RestConfig restConfig;
    //private String URL_BASE;
    //private final static String URL_BASE = "http://192.168.2.9";
    //private String URL_LOGIN = URL_BASE + "/CPServiceRelease/Service/Imp/ExternalService/MobileAppLogicService.svc/rest/login";
    //private String URL_GET_MENUS = URL_BASE + "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/{USERCD}/xptcs/menus";
    //private String URL_GET_ROLES = URL_BASE + "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/roles";
    //private String URL_GET_PERMISSIONS = URL_BASE + "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/operations";

//    public void setURL_BASE(String url_base){
//        this.URL_BASE=url_base;
//    }
//
//    public String getURL_BASE(){
//        return this.URL_BASE;
//    }
//
//    private String getURL_LOGIN(){
//        return getURL_BASE()+"/CPServiceRelease/Service/Imp/ExternalService/MobileAppLogicService.svc/rest/login";
//    }
//
//    private String getURL_GET_MENUS(String userCd){
//        return getURL_BASE()+"/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/{USERCD}/xptcs/menus".replace("{USERCD}", userCd);
//    }
//
//    private String getURL_GET_ROLES(String userCd){
//        return getURL_BASE()+ "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/roles".replace("{USERCD}", userCd);
//    }
//
//    private String getURL_GET_PERMISSIONS(String userCd){
//        return getURL_BASE()+ "/CPServiceRelease/Service/Imp/ExternalService/SubSysLogicService.svc/rest/xptcs/{USERCD}/operations".replace("{USERCD}", userCd);
//    }

    @Override
    public CPUser login(String username, String password) {
        CPUser result = null;
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(restConfig.getURL_LOGIN());
            request.setHeader("Content-Type", "application/json;charset=UTF-8");

            JSONObject params = new JSONObject();
            params.put("userCd", username);
            params.put("password", Md5Util.encode(password));
            StringEntity entity = new StringEntity(params.toString(), "UTF-8");
            request.setEntity(entity);
            logger.info("request =" + request);

            HttpResponse response = client.execute(request);
            logger.info("response =" + response);

            BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder responseString = new StringBuilder();
            String line = "";
            while ((line = breader.readLine()) != null) {
                responseString.append(line);
            }
            breader.close();
            String repsonseStr = responseString.toString();
            logger.info("repsonseStr =" + repsonseStr);
            //System.out.println("repsonseStr =" + repsonseStr);

            result = JSONObject.parseObject(repsonseStr, CPUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(String userCd) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
            HttpClient client = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).build();
            //获取角色
            HttpGet request = new HttpGet(restConfig.getURL_GET_ROLES(userCd));
            request.setConfig(requestConfig);
            request.setHeader("Content-Type", "application/json;charset=UTF-8");
            logger.info("request =" + request);
            HttpResponse response = client.execute(request);
            logger.info("response =" + response);

            String repsonseStr = getResponseString(response);
            logger.info("repsonseStr =" + repsonseStr);

            JSONArray jsonArray = JSONArray.parseArray(repsonseStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                info.addRole(jsonArray.getJSONObject(i).getString("roleCd"));
            }

            //获取权限
            request = new HttpGet(restConfig.getURL_GET_PERMISSIONS(userCd));
            request.setConfig(requestConfig);
            logger.info("request =" + request);
            response = client.execute(request);
            logger.info("response =" + response);

            repsonseStr = getResponseString(response);
            logger.info("repsonseStr =" + repsonseStr);

            jsonArray = JSONArray.parseArray(repsonseStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                info.addStringPermission(jsonArray.getJSONObject(i).getString("OperationCd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        logger.info("result =" + info);
        return info;
    }

    @Override
    public List<Menu> getMenuInfos(String userCd) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(restConfig.getURL_GET_MENUS(userCd));
            request.setHeader("Content-Type", "application/json;charset=UTF-8");
            logger.info("request =" + request);
            HttpResponse response = client.execute(request);
            logger.info("response =" + response);

            BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder responseString = new StringBuilder();
            String line = "";
            while ((line = breader.readLine()) != null) {
                responseString.append(line);
            }
            breader.close();
            String repsonseStr = responseString.toString();
            logger.info("repsonseStr =" + repsonseStr);

            List<CPMenuInfo> cpMenuInfos = JSONArray.parseArray(repsonseStr, CPMenuInfo.class);
            Menu root = creatTree(cpMenuInfos);
            return root.getChildren();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Menu creatTree(List<CPMenuInfo> list) {
        Menu root = new Menu();
        Stack<Menu> stack = new Stack<Menu>();
        stack.push(root);
        int i = 0;
        while (!stack.isEmpty()) {
            Menu info = stack.pop();
            i = 0;
            while (i < list.size()) {
                CPMenuInfo cp = list.get(i);
                if ((info.getId() == null && cp.SuperMenuId == null) || (info.getId() != null && info.getId().equals(cp.SuperMenuId))) {
                    Menu sub = cp.convertToMenuInfo();
                    info.addChild(sub);
                    stack.push(sub);
                    list.remove(i);
                } else {
                    i++;
                }
            }
        }
        return root;
    }

    private String getResponseString(HttpResponse httpResponse) throws IOException {
        BufferedReader breader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        StringBuilder responseString = new StringBuilder();
        String line = "";
        while ((line = breader.readLine()) != null) {
            responseString.append(line);
        }
        breader.close();
        return responseString.toString();
    }
}
