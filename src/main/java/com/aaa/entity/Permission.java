package com.aaa.entity;

import java.util.List;

/**
 * Demo class
 *
 * @author 尚冠峰
 * @date 2018/12/19
 */
public class Permission {
    private int id;
    private String name;
    private List<Children> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Children> getList() {
        return list;
    }

    public void setList(List<Children> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
