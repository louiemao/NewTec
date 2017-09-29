package com.sws.newtec.modules.module1.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by george on 15-7-6.
 */
public class Server {

    @Id
    private String id;
    private String code;
    private String desc;
    private List<HashMap<String,String>> configuration;
    private String username;
    private String password;
    private String owner;
    private String remark;
    private Date rgstDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRgstDate() {
        return rgstDate;
    }

    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }


    public List<HashMap<String,String>> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<HashMap<String,String>> configuration) {
        this.configuration = configuration;
    }
}
