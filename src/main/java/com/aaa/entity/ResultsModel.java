package com.aaa.entity;

/**
 * @author
 * @description 前后台数据库操作返回信息模型
 * @company
 * @classname
 *
 */
public class ResultsModel {
    private int retStatus = 0;//0成功，1失败
    private String message;//返回提示信息
    private Object obj;//返回内容

    public int getRetStatus() {
        return retStatus;
    }

    public void setRetStatus(int retStatus) {
        this.retStatus = retStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


}
