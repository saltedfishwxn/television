package com.aaa.util;

import com.aaa.entity.User;
import com.aaa.systemconst.ISystemConst;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class EmpUtil {

    private static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取session中 当前登陆的员工对象
     * @param
     * @return user
     */
    public static User getEmp() {
        return (User) getSession().getAttribute(ISystemConst.EMP_IN_SESSION);
    }

    /***
     * 存储当前登陆员工到 session中
     * @param emp
     */
    public static void setEmp(User emp) {
        getSession().setAttribute(ISystemConst.EMP_IN_SESSION, emp);
    }

    public static void removeEmp() {
        HttpSession session = getSession();
        session.invalidate();
    }

}