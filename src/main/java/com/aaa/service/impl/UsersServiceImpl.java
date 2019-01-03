package com.aaa.service.impl;

import com.aaa.dao.UsersMapper;
import com.aaa.entity.Users;
import com.aaa.service.UsersService;
import com.aaa.util.LxlExeclUtil;
import com.aaa.util.LxlShiroUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper dao;

    @Override
    public Map<String, Object> listRoles() {
        Map map = new HashMap();
        List<Map<String, Object>> listRoles = null;
        try {
            listRoles = dao.listRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("listRoles", listRoles);
        return map;
    }

    /**
     * &gt;&lt;
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> listUser(Map<String, Object> query) {
        Map map = new HashMap();
        int pageNo = Integer.parseInt(query.get("pageNo") + "");
        int pageSize = Integer.parseInt(query.get("pageSize") + "");
        PageHelper.startPage(pageNo, pageSize);
        List<Users> listUser = dao.listUser(query);
        Integer totalUser = dao.getTotalUser(query);
        map.put("listUser", listUser);
        map.put("totalUser", totalUser);
        return map;
    }

    /**
     * 判断手机号是否已存在
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> phoneChange(Map<String, Object> query) {
        //需要返回的map
        Map<String, Object> map = new HashMap<>();
        //标志
        int flag = 0;
        List<Map<String, Object>> list = dao.phoneChange(query);
            if(list.size()>0){
                map.put("phoneChangeInfo","该手机号已存在");
            }else{
                map.put("phoneChangeInfo","合格");
            }
        /*}*/
        return map;
    }

    /**
     * 为此员工生成登录名、登录密码
     * 添加
     * 返回信息
     * @param users
     * @return
     */
    @Override
    public Map<String, Object> addUser(Users users) {
        //需要返回的map
        Map<String, Object> map = new HashMap<>();
        //标志
        int flag = 0;
        try {
            //初始登录名为手机号，初始密码为123456 ,入职日期为当前日期  盐值和加密后的密码 添加user_role
            users.setLoginName(users.getPhone());
            users.setPassword("123456");
            LxlShiroUtil.encryptPassword(users);
            users.setPath("/crm"+users.getPath());
            dao.addUser(users);
            //获得主键
            List<Map<String, Object>> userIds = dao.getUserId(users);
            users.setId(Integer.parseInt(userIds.get(0).get("id").toString()));
            dao.addUserToRole(users);
            flag=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag > 0) {
            map.put("addUserInfo", "添加成功");
        } else {
            map.put("addUserInfo", "添加失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> editUser(Users users) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            users.getPath();
            dao.editUser(users);
            dao.updateUserRole(users);
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("editUserInfo","修改成功");
        }else{
            map.put("editUserInfo","修改失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> toExcelUsers(Map<String, List<Users>> query) {
        HashMap<String, Object> map = new HashMap<>();
        int flag = 0;
        try {
            List<Users> list = query.get("excelUsers");
            for(Users u : list){
                if(u.getSex().equals("0")){
                    u.setSex("男");
                }
                if(u.getSex().equals("1")){
                    u.setSex("女");
                }
                if(u.getStatus()==0){
                    u.setStatusMsg("在职");
                }
                if(u.getStatus()==1){
                    u.setStatusMsg("离职");
                }
                System.out.println(u);
            }
            OutputStream out = new FileOutputStream("d://users.xls");
            Map<String, String> fields = new HashMap<String, String>();
            fields.put("id", "编号");
            fields.put("name", "员工姓名");
            fields.put("sex", "员工性别");
            fields.put("age", "员工年龄");
            fields.put("phone", "员工手机号");
            fields.put("statusMsg", "员工状态");
            fields.put("createTime", "员工入职时间");
            fields.put("email", "员工邮箱");
            fields.put("roleName", "员工角色");
            //List<Users>数据    new FileOutputStream("d://users.xls");路径   Map<String, String> fields字段
            LxlExeclUtil.ListtoExecl(list, out, fields);
            out.close();
            flag=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag>0){
            map.put("excelUsersInfo","导出成功");
        }else{
            map.put("excelUsersInfo","导出失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> inExcelUsers(String path) {
        HashMap<String, Object> m = new HashMap<>();
        int ret = 0;
        try {
            InputStream in = new FileInputStream(path);
            Map<String, String> fieldd = new HashMap<String, String>();
            fieldd.put("员工状态", "statusMsg");
            fieldd.put("员工手机号", "phone");
            fieldd.put("员工性别", "sex");
            fieldd.put("员工姓名", "name");
            fieldd.put("编号", "id");
            fieldd.put("员工年龄", "age");
            fieldd.put("员工邮箱", "email");
            fieldd.put("员工角色", "roleName");
            List<Users> resultList = new ArrayList<>();
            resultList = LxlExeclUtil.ExecltoList(in, Users.class, fieldd);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Users user : resultList){
                //判断手机号是否已经存在
                Map<String, Object> phone = new HashMap<>();
                BigDecimal bd = new BigDecimal(user.getPhone());
                String str = bd.toPlainString();
                phone.put("phone",str);
                Map<String, Object> stringObjectMap = phoneChange(phone);
                if(stringObjectMap.get("phoneChangeInfo").equals("合格")){
                    user.setPhone(str);
                    if(user.getStatusMsg().equals("在职")){
                        user.setStatus(0);
                    }
                    if(user.getStatusMsg().equals("离职")){
                        user.setStatus(1);
                    }
                    if(user.getSex().equals("男")){
                        user.setSex("0");
                    }
                    if(user.getSex().equals("女")){
                        user.setSex("1");
                    }
                    user.setCreateTime(new Date());
                    addUser(user);
                }
            }
            ret=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret>0){
            m.put("inExcelUsersInfo","导入成功");
        }else {
            m.put("inExcelUsersInfo","导入失败");
        }
        return m;
    }
}
