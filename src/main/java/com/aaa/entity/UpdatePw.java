package com.aaa.entity;

/**
 * Demo class
 *
 * @author 尚冠峰
 * @date 2018/12/24
 */
public class UpdatePw {
    private String loginname;
    private String password;
    private String pass;
    private String checkPass;

    @Override
    public String toString() {
        return "UpdatePw{" +
                "loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", pass='" + pass + '\'' +
                ", checkPass='" + checkPass + '\'' +
                '}';
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }
}
