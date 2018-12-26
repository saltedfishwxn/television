package com.aaa.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Complaints {
    private Integer id;

    private Integer customerid;

    private Integer userid;

    private String numbers;

    private String opinions;

    private String describes;

    private String theme;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    private String status;

    private String degree;

    private String way;

    private String connections;

    private Integer recordid;

    private String result;

    private Integer endid;

    private String content;

    private Integer isdel;

    private String comment;






    private String customername;

    //
    private String type;

    private String username;

    private String recordname;

    private String sort;

    private String endname;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtimea;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtimeb;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtimea;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtimeb;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecordname() {
        return recordname;
    }

    public void setRecordname(String recordname) {
        this.recordname = recordname;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Date getCreatetimea() {
        return createtimea;
    }

    public void setCreatetimea(Date createtimea) {
        this.createtimea = createtimea;
    }

    public Date getCreatetimeb() {
        return createtimeb;
    }

    public void setCreatetimeb(Date createtimeb) {
        this.createtimeb = createtimeb;
    }

    public Date getEndtimea() {
        return endtimea;
    }

    public void setEndtimea(Date endtimea) {
        this.endtimea = endtimea;
    }

    public Date getEndtimeb() {
        return endtimeb;
    }

    public void setEndtimeb(Date endtimeb) {
        this.endtimeb = endtimeb;
    }

    public String getEndname() {
        return endname;
    }

    public void setEndname(String endname) {
        this.endname = endname;
    }













    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getOpinions() {
        return opinions;
    }

    public void setOpinions(String opinions) {
        this.opinions = opinions == null ? null : opinions.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections == null ? null : connections.trim();
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getEndid() {
        return endid;
    }

    public void setEndid(Integer endid) {
        this.endid = endid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Complaints() {
    }

    public Complaints(Integer id, Integer customerid, Integer userid, String numbers, String opinions, String describes, String theme, Date createtime, Date endtime, String status, String degree, String way, String connections, Integer recordid, String result, Integer endid, String content, Integer isdel, String comment, String customername, String type, String username, String recordname, String sort, String endname, Date createtimea, Date createtimeb, Date endtimea, Date endtimeb) {
        this.id = id;
        this.customerid = customerid;
        this.userid = userid;
        this.numbers = numbers;
        this.opinions = opinions;
        this.describes = describes;
        this.theme = theme;
        this.createtime = createtime;
        this.endtime = endtime;
        this.status = status;
        this.degree = degree;
        this.way = way;
        this.connections = connections;
        this.recordid = recordid;
        this.result = result;
        this.endid = endid;
        this.content = content;
        this.isdel = isdel;
        this.comment = comment;
        this.customername = customername;
        this.type = type;
        this.username = username;
        this.recordname = recordname;
        this.sort = sort;
        this.endname = endname;
        this.createtimea = createtimea;
        this.createtimeb = createtimeb;
        this.endtimea = endtimea;
        this.endtimeb = endtimeb;
    }
}