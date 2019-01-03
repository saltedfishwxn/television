package com.aaa.service.impl;

import com.aaa.dao.ComplaintsMapper;
import com.aaa.entity.Complaints;
import com.aaa.entity.LxlEcharts;
import com.aaa.service.ComplaintsService;
import com.aaa.util.LxlExcel;
import com.aaa.util.LxlExeclUtil;
import com.aaa.util.LxlUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ComplaintsServiceImpl implements ComplaintsService {

    @Autowired
    private ComplaintsMapper dao;

    @Value("${fileupload.path}")
    private String filePath;

    @Override
    public Map<String, Object> getUserByLoginName(String shiroUserName) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = dao.getUserByLoginName(shiroUserName);
        if(list.size()==1){
            map.put("shiroUserName",list.get(0).get("name"));
        }
        return map;
    }

    @Override
    public Map<String, Object> updateInfo(Map<String, Object> query) {
        dao.updateInfo(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("a","a");
        return map;
    }

    @Override
    public Map<String, Object> submitResult(Complaints complaints) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            complaints.setEndtime(new Date());
            dao.submitResult(complaints);
            flag=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("submitResult","提交成功");
        }else{
            map.put("submitResult","提交失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> submitSendMsgForm(Complaints complaints) {
        System.out.println(complaints);
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            //发送短信
            LxlUtil.sendMsg(complaints.getConnections(),complaints.getSendMsg());
            dao.editComplaints(complaints);
            flag=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("submitSendMsgForm","发送成功");
        }else{
            map.put("submitSendMsgForm","发送失败");
        }
        return map;
    }

    /********************************************************************/

    @Override
    public Map<String, Object> listComplaints(Map<String, Object> query) {
        Map map = new HashMap();
        int pageNo = Integer.parseInt(query.get("pageNo") + "");
        int pageSize = Integer.parseInt(query.get("pageSize") + "");
        PageHelper.startPage(pageNo, pageSize);
        List<Complaints> listComplaints = null;
        Integer totalComplaints = null;
        String sort = query.get("sort").toString();
        if(sort.equals("产品投诉")){
            query.put("userid","产品投诉");
        }
        if(sort.equals("服务投诉")){
            query.put("numbers","numbers");
        }
        if(sort.equals("客户意见")){
            query.put("opinions","opinions");
        }
        if(sort.equals("其他")){
            query.put("describes","describes");
        }
        try {
            listComplaints = dao.listComplaints(query);
            for(Complaints complaint:listComplaints){
                //是否是产品投诉
                if(complaint.getNumbers()!=null){
                    complaint.setSort("产品投诉");
                }
                //是否是投诉员工 是的话
                if(complaint.getUserid()!=null){
                    complaint.setSort("服务投诉");
                    complaint.setUsername(dao.getRecordById(complaint.getUserid()).get(0).get("name").toString());
                }
                //是否是客户意见
                if(complaint.getOpinions()!=null){
                    complaint.setSort("客户意见");
                }
                //是否是其他
                if(complaint.getDescribes()!=null){
                    complaint.setSort("其他");
                }
                //解决者
                if(complaint.getEndid()!=null){
                    complaint.setEndname(dao.getRecordById(complaint.getEndid()).get(0).get("name").toString());
                }
                //录入人
                if(complaint.getRecordid()!=null){
                    complaint.setRecordname(dao.getRecordById(complaint.getRecordid()).get(0).get("name").toString());
                }
                //客户姓名
                complaint.setCustomername(dao.getCustomsById(complaint.getCustomerid()).get(0).get("customerName").toString());
            }
            totalComplaints = dao.getTotalComplaints(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("listComplaints", listComplaints);
        map.put("totalComplaints", totalComplaints);
        return map;
    }

    @Override
    public Map<String, Object> listCustoms() {
        Map map = new HashMap();
        List<Map<String, Object>> listComplaints = null;
        try {
            listComplaints = dao.listCustoms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("listCustoms", listComplaints);
        return map;
    }

    @Override
    public Map<String, Object> listUsers() {
        Map map = new HashMap();
        List<Map<String, Object>> listUsers = null;
        try {
            listUsers = dao.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("listUsers", listUsers);
        return map;
    }

    @Override
    public Map<String, Object> addSeenYi(Map<String,Object> query) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = null;
        try {
            list = dao.addSeenYi(query.get("input23").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size()>0) {
            map.put("addSeenYiInfoo", "成交编号存在");
        } else {
            map.put("addSeenYiInfoo", "成交编号不存在");
        }
        return map;
    }

    /**
     * 根据用户名获取此用户的所有信息
     *
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> addSeenEr(Map<String,Object> query) {
        List<Map<String, Object>> list = dao.addSeenEr(query.get("input23").toString());
        Map<String, Object> map = new HashMap<>();
        if (list.size() > 0) {
            map.put("addSeenErInfoo", "此员工存在");
        } else {
            map.put("addSeenErInfoo", "员工不存在");
            for (Map<String, Object> userid : list) {
                map.put("userid" + userid, userid);
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteComplaintsById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        dao.deleteComplaintsById(id);
        flag = 1;
        if(flag>0){
            map.put("deleteInfo","删除成功");
        }else{
            map.put("deleteInfo","删除失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> addComplaints(Complaints complaints) {
        Map<String, Object> map = new HashMap<>();
        int flag = 0;
        if (complaints.getUsername() != null && complaints.getUsername() != "") {
                complaints.setUserid(Integer.parseInt(dao.addSeenEr(complaints.getUsername()).get(0).get("id").toString()));
                //投诉状态 默认0 未处理
                complaints.setStatus("0");
        }
        dao.addComplaints(complaints);
        flag=1;
        if (flag > 0) {
            map.put("addComplaint", "添加成功");
        } else {
            map.put("addComplaint", "添加失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> editComplaints(Complaints complaints) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            dao.editComplaints(complaints);
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("editInfo","修改成功");
        }else{
            map.put("editInfo","修改失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> inExcel(String path) throws Exception {
        List<Map<String, Object>> map3 =  LxlExcel.readXLSX(path);
        HashMap<String, Object> m = new HashMap<>();
        int ret = 0;
        for(Map<String, Object> map : map3){
            Complaints complaints = new Complaints();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                complaints.setCustomerid(Integer.parseInt(map.get("customerid").toString()));
                if(map.get("sort").equals("产品投诉")){
                    complaints.setNumbers(map.get("numbers").toString());
                }
                if(map.get("sort").equals("服务投诉")){
                    complaints.setUserid(Integer.parseInt(map.get("userid").toString()));
                }
                if(map.get("sort").equals("客户意见")){
                    complaints.setOpinions(map.get("opinions").toString());
                }
                if(map.get("sort").equals("其他")){
                    complaints.setDescribes(map.get("describes").toString());
                }
                complaints.setTheme(map.get("theme").toString());
                complaints.setCreatetime(sdf.parse(map.get("createtime").toString()));
                complaints.setEndtime(sdf.parse(map.get("endtime").toString()));
                complaints.setStatus(map.get("status").toString());
                complaints.setDegree(map.get("degree").toString());
                complaints.setWay(map.get("way").toString());
                complaints.setConnections(map.get("connections").toString());
                complaints.setRecordid(Integer.parseInt(map.get("recordid").toString()));
                complaints.setResult(map.get("result").toString());
                complaints.setEndid(Integer.parseInt(map.get("endid").toString()));
                complaints.setContent(map.get("content").toString());
                complaints.setComment(map.get("comment").toString());
                ret=1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            dao.addComplaints(complaints);
        }
        if (ret>0){
            m.put("pa","导入成功");
        }else {
            m.put("pa","导入失败");
        }
        return m;
    }

    @Override
    public Map<String, Object> toExcel(Map<String, List<Complaints>> query) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            List<Complaints> list = query.get("toExcel");
            OutputStream out = new FileOutputStream("d://aaa.xls");
            Map<String, String> fields = new HashMap<String, String>();
            fields.put("id", "编号");
            fields.put("customerid", "客户ID");
            fields.put("customername", "客户姓名");
            fields.put("sort", "投诉类型");
            fields.put("userid", "投诉员工ID");
            fields.put("username", "投诉员工name");
            fields.put("numbers", "投诉成交单号");
            fields.put("opinions", "客户意见");
            fields.put("describes", "其他");
            fields.put("theme", "投诉主题");
            fields.put("createtime", "创建时间");
            fields.put("endtime", "结束时间");
            fields.put("status", "投诉状态");
            fields.put("degree", "紧急程度");
            fields.put("way", "投诉方式");
            fields.put("connections", "联系方式");
            fields.put("recordid", "录入人ID");
            fields.put("recordname", "录入人name");
            fields.put("result", "投诉结果");
            fields.put("endid", "处理人ID");
            fields.put("endname", "处理人name");
            fields.put("content", "投诉内容");
            fields.put("comment", "编备注号");
            fields.put("isdel", "是否删除");
            LxlExeclUtil.ListtoExecl(list, out, fields);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("pa","导出成功");
        }else{
            map.put("pa","导出失败");
        }
        return map;
    }

    /**
     *
     * @param echarts
     * @return
     *    userid       select count(*) value from complaints where userId is not NULL;
     */
    @Override
    public Map<String, Object> doStatOth(LxlEcharts echarts) {
        //返回的map
        Map<String, Object> m = new HashMap<>();
        //返回的[]
        List<Integer> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        int flag = 0;
        List<Map<String, Object>> lists = null;
        try {
            String sort = echarts.getSort();
            if(sort.equals("userid")){
                lists = dao.useridStat(echarts);
            }else{
                lists = dao.numbersStat(echarts);
            }
            if(lists.size()>0){
                for(Map<String, Object> list :lists){
                    a.add(Integer.parseInt(list.get("cou").toString()));
                    b.add(list.get("nam").toString());
                }
                flag=1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(flag>0){
            m.put("pa",a);
            m.put("pb",b);
        }else{
            m.put("pa","暂无统计数据");
        }
        return m;
    }

    @Override
    public String doStat(LxlEcharts echarts) {
        //返回的op [{},{}]
        List<LxlEcharts> op = new ArrayList<>();
        int flag=0;
        try {
            String sort = echarts.getSort();
            if(sort.equals("sort")){
                List<Map<String, Object>> lists = dao.sortStat(echarts);
                if(lists.size()>0){
                    //返回的op的{}
                    op.add(new LxlEcharts("产品投诉",Integer.parseInt(lists.get(0).get("v").toString())));
                    op.add(new LxlEcharts("服务投诉",Integer.parseInt(lists.get(0).get("va").toString())));
                    op.add(new LxlEcharts("客户意见",Integer.parseInt(lists.get(0).get("val").toString())));
                    op.add(new LxlEcharts("其他",Integer.parseInt(lists.get(0).get("valu").toString())));
                }
                flag=1;
            }else  {
                List<Map<String, Object>> lists = dao.otherStat(echarts);
                if(lists.size()>0){
                    if (sort.equals("degree")) {
                        op.add(new LxlEcharts("一般", Integer.parseInt(lists.get(0).get("value").toString())));
                    } else if (sort.equals("status")) {
                        op.add(new LxlEcharts("待处理", Integer.parseInt(lists.get(0).get("value").toString())));
                    }else{
                        op.add(new LxlEcharts("电话", Integer.parseInt(lists.get(0).get("value").toString())));
                    }
                }
                if(lists.size()>1){
                    if (sort.equals("degree")) {
                        op.add(new LxlEcharts("紧急", Integer.parseInt(lists.get(1).get("value").toString())));
                    } else if (sort.equals("status")) {
                        op.add(new LxlEcharts("正在处理", Integer.parseInt(lists.get(1).get("value").toString())));
                    }else{
                        op.add(new LxlEcharts("传真", Integer.parseInt(lists.get(1).get("value").toString())));
                    }
                }
                if(lists.size()>2){
                    if (sort.equals("degree")) {
                        op.add(new LxlEcharts("非常紧急", Integer.parseInt(lists.get(2).get("value").toString())));
                    } else if (sort.equals("status")) {
                        op.add(new LxlEcharts("已处理", Integer.parseInt(lists.get(2).get("value").toString())));
                    }else{
                        op.add(new LxlEcharts("邮件", Integer.parseInt(lists.get(2).get("value").toString())));
                    }
                }
                if(lists.size()>3){
                    if (sort.equals("status")) {
                        op.add(new LxlEcharts("其他", Integer.parseInt(lists.get(3).get("value").toString())));
                    }else{
                        op.add(new LxlEcharts("其他", Integer.parseInt(lists.get(3).get("value").toString())));
                    }
                }
                flag=1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String data = null;
        if(flag>0){
            data = JSON.toJSONString(op);
        }else{
            op.add(new LxlEcharts("暂无统计数据","暂无统计数据"));
            data = JSON.toJSONString(op);
        }
        return data;
    }



}
