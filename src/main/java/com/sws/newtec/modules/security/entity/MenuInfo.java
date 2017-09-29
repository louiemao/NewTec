package com.sws.newtec.modules.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Louie on 2015/7/28.
 */
@JsonIgnoreProperties(value = {"parent"})
@Entity
@Table(name = "shiro_menu")
public class MenuInfo {
    private String id;
    private String label;
    private String link;
    private MenuInfo parent;
    private List<MenuInfo> children = new ArrayList<MenuInfo>(0);
    //    private Set<Role> roles=new HashSet<Role>(0);

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public MenuInfo getParent() {
        return parent;
    }

    public void setParent(MenuInfo parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
    public List<MenuInfo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfo> children) {
        this.children = children;
    }

    public void addChild(MenuInfo child) {
        if (this.children == null) {
            this.children = new ArrayList<MenuInfo>();
        }
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParent(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuInfo) {
            return this.id.equals(((MenuInfo) obj).getId());
        } else {
            return super.equals(obj);
        }
    }


    //    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "shiro_menu_role", joinColumns = { @JoinColumn(name = "menu_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}
