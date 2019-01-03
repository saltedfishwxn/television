package com.aaa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description :自动生成下次回访时间的工具类
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/22 002216:12
 */
public  class ReturnNextFollowDate {

    /**
     * 根据客户级别 判断下一次跟进时间
     * @param level
     * @return
     */
    public static String returnNextDate(Date now, String level){
        //Date date = new Date();
        System.out.println("当前时间是:"+now);
        int day = 0;
        switch (level){
            case "H":
                day = 2;
                break;
            case "A":
                day = 5;
                break;
            case "B":
                day=14;
                break;
            case "C":
                day=30;
                break;
            default:
                return null;

        }
        //获取对应日期天后的时间
        Date dateAfter = getDateAfter(now, day);
        //将日期转化为没有时分秒的字符串日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String format = sdf.format(dateAfter);

        return format;
    }



    /**

     * 得到几天后的时间

     * @param d

     * @param day

     * @return

     */

    public static Date getDateAfter(Date d, int day){

        Calendar now =Calendar.getInstance();

        now.setTime(d);

        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);

        return now.getTime();

    }
}
