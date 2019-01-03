package com.aaa.controller;

import com.aaa.service.IAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName Achievement
 * @Description 员工的业绩统计
 * @Author LP
 * @Date 2018/12/28 10:48
 * @Version 1.0
 **/
@Controller
@RequestMapping("/achievement")
public class AchievementController {
    @Autowired
    private IAchievementService achievementService;

    /**
     * 跳转页面
     * @return
     */
    @RequestMapping("/view")
    public String view(){
        return "achievementStatistics";
    }

    /**
     * 按条件对时间段内业绩查询
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping("/statistics")
    public Map<String,Object> statistics(@RequestBody Map<String,Object> query){
        return achievementService.statistics(query);
    }

    /**
     * 跳转展示时间
     * @return
     */
    @RequestMapping("/time")
    public String time(){
        return "time";
    }
}
