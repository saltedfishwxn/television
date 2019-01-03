package com.aaa.util;

/**
 * @ClassName MyException
 * @Description 判断添加用户是否重复异常
 * @Author LP
 * @Date 2018/12/27 9:14
 * @Version 1.0
 **/
public class MyException extends Exception{
    public MyException(){
        super();
    }
    public MyException(String message){
        super(message);
    }
}
