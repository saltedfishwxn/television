package com.aaa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Demo class
 * 合同搜索条件
 * @author 尚冠峰
 * @date 2018/12/20
 */
public class OrderSearch {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
    private String name;
    private String cord;
    private String counselor;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCord() {
        return cord;
    }

    public void setCord(String cord) {
        this.cord = cord;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    @Override
    public String toString() {
        return "OrderSearch{" +
                "start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                ", cord='" + cord + '\'' +
                ", counselor='" + counselor + '\'' +
                '}';
    }
}
