package com.sws.newtec.modules.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louie on 2015/7/28.
 */
@JsonIgnoreProperties(value = {"parent"})
public class Menu {
    private String id;
    private String label;
    private String link;
    private Menu parent;
    private List<Menu> children = new ArrayList<Menu>(0);
    private int sort;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void addChild(Menu child) {
        if (this.children == null) {
            this.children = new ArrayList<Menu>();
        }
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParent(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu) {
            return this.id.equals(((Menu) obj).getId());
        } else {
            return super.equals(obj);
        }
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
