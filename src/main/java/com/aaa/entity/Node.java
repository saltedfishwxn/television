package com.aaa.entity;

import java.util.List;

/**
 * className:Node
 * discription:后台树
 * author:LP
 * createTime:2018-12-15 09:24
 */
public class Node {

    private Integer id;
    private String label;//节点名称
    private Integer pid; //父节点
    private String iconClass;//节点图标
    private String url;//url 地址
    private List<Node> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
