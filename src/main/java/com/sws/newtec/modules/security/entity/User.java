package com.sws.newtec.modules.security.entity;

// Generated 2015-2-3 10:43:00 by Hibernate Tools 4.0.0

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

//@JsonIgnoreProperties(value = {"password"})
@Entity
@Table(name = "shiro_user", catalog = "newtec")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6980093847795726310L;
    private String id;
    private String username;
    private String email;
    private String password;
    private Date registerTime;
    private List<Role> roles = new ArrayList<Role>(0);

    public User() {
    }


    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_time", length = 19)
    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @JsonIgnoreProperties(value = {"users", "permissions", "menuInfos"})
    @ManyToMany(fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "shiro_user_role", catalog = "newtec", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "[ username=" + getUsername() + " email=" + getEmail() + " password=" + getPassword() + "]";
    }

    @Deprecated
    public List<MenuInfo> findMenuInfos() {
        MenuInfo root = new MenuInfo();
        Stack<MenuInfo> stack = new Stack<MenuInfo>();
        //获取所有菜单
        List<MenuInfo> list = new ArrayList<MenuInfo>();
        for (Role r : getRoles()) {
            list.addAll(r.getMenuInfos());
        }
        //构造菜单树
        stack.push(root);
        int i = 0;
        while (!stack.isEmpty()) {
            MenuInfo info = stack.pop();
            i = 0;
            while (i < list.size()) {
                MenuInfo sub = list.get(i);
                if ((info.getId() == null && sub.getParent() == null) || (info.getId()!=null&&info.getId().equals(sub.getParent().getId()))) {
                    info.addChild(sub);
                    stack.push(sub);
                    sub.setChildren(null);
                    list.remove(sub);
                }else{
                    i++;
                }
            }
        }

        return root.getChildren();
    }
}
