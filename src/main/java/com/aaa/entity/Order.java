package com.aaa.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 合同
 *
 * @author 尚冠峰
 * @date 2018/12/20
 */
public class Order {
    private int id;
    private String cord;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String counselor;
    private String name;
    private String tel;
    private String adress;
    private String ktype;
    private double discounts;
    private double eprice;
    private double other;
    private String pay;
    private double allprice;
    private Integer pid;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCord() {
        return cord;
    }
    public void setCord(String cord) {
        this.cord = cord;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getKtype() {
        return ktype;
    }

    public void setKtype(String ktype) {
        this.ktype = ktype;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public double getEprice() {
        return eprice;
    }

    public void setEprice(double eprice) {
        this.eprice = eprice;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public double getAllprice() {
        return allprice;
    }

    public void setAllprice(double allprice) {
        this.allprice = allprice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cord='" + cord + '\'' +
                ", date=" + date +
                ", counselor='" + counselor + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", adress='" + adress + '\'' +
                ", ktype='" + ktype + '\'' +
                ", discounts=" + discounts +
                ", eprice=" + eprice +
                ", other=" + other +
                ", pay='" + pay + '\'' +
                ", allprice=" + allprice +
                ", pid=" + pid +
                ", url='" + url + '\'' +
                '}';
    }


}
