package com.aaa.entity;

/**
 * 导航菜单
 *
 * @author 尚冠峰
 * @date 2018/12/19
 */
public class Children {
    private int parentid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String url;



    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Children{" +
                "parentid=" + parentid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
