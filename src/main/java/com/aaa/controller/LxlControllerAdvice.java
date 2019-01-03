package com.aaa.controller;

import com.aaa.entity.ResultModel;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class LxlControllerAdvice {

    /**
     * 全局异常捕获处理
     * @param e
     * @return
     */
    public ResultModel errorHandler(Exception e){
        ResultModel rm = new ResultModel();
        rm.setResultStatus(1);
        rm.setMessage("hahaha"+e.getMessage());
        return rm;
    }

}
