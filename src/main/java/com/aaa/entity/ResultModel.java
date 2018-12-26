package com.aaa.entity;

/**
 * @ClassName ResultModel
 * @Description 前台数据库操作返回
 * @Author LP
 * @Date 2018/12/8 8:57
 * @Version 1.0
 **/
public class ResultModel {
    private Integer resultStatus;
    private String message;
    private Object object;

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
