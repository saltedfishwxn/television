package com.aaa.service;

import com.aaa.entity.Complaints;
import com.aaa.entity.LxlEcharts;

import java.util.List;
import java.util.Map;

public interface ComplaintsService {

    /*****************************************************/

    Map<String, Object> updateInfo(Map<String, Object> query);

    Map<String, Object> submitResult(Complaints complaints);

    Map<String,Object> listComplaints(Map<String, Object> query);

    Map<String,Object> listCustoms();

    Map<String, Object> listUsers();

    Map<String,Object> addSeenYi(Map<String,Object> query);

    Map<String,Object> addSeenEr(Map<String,Object> query);

    Map<String, Object> deleteComplaintsById(Integer id);

    Map<String,Object> addComplaints(Complaints complaints);

    Map<String, Object> editComplaints(Complaints complaints);

    Map<String, Object> inExcel(List<Map<String, Object>> map);

    Map<String, Object> toExcel(Map<String, List<Complaints>> query) throws Exception;

    Map<String,Object> doStatOth(LxlEcharts echarts);

    String doStat(LxlEcharts echarts);
}
