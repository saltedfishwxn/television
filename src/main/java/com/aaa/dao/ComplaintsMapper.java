package com.aaa.dao;

import com.aaa.entity.Complaints;
import com.aaa.entity.LxlEcharts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ComplaintsMapper {
    int insert(Complaints record);

    int insertSelective(Complaints record);

    List<Map<String, Object>> getUserByLoginName(String shiroUserName);

    void updateInfo(Map<String, Object> query);

    void submitResult(Complaints complaints);

    List<Complaints> listComplaints(Map<String, Object> query);

    //根据员工ID获取员工信息
    List<Map<String,Object>> getCustomsById(Integer userid);

    //录入人 根据用户名id获取员工信息
    List<Map<String,Object>> getRecordById(Integer recordid);

    int getTotalComplaints(Map<String, Object> query);

    List<Map<String,Object>> listCustoms();

    List<Map<String, Object>> listUsers();

    List<Map<String, Object>> addSeenYi(String cord);

    List<Map<String,Object>> addSeenEr(String name);

    void deleteComplaintsById(Integer id);

    void addComplaints(Complaints complaints);

    void editComplaints(Complaints complaints);

    List<Map<String,Object>> sortStat(LxlEcharts echarts);

    List<Map<String, Object>> useridStat(LxlEcharts echarts);

    List<Map<String, Object>> numbersStat(LxlEcharts echarts);

    List<Map<String,Object>> otherStat(LxlEcharts echarts);

    void submitSendMsgForm(Complaints complaints);
}