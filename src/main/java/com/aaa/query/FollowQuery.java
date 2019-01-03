package com.aaa.query;

import lombok.Data;

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
 * @Description : 跟进客户待处理页面查询条件实体类
 * customerLevel,customerName,customerTel,empName,followStatus
 * ---------------------------------
 * @Author : An
 * @Date : Create in 2018/12/20 002010:53
 */
@Data
public class FollowQuery {
    private String customerLevel;
    private String customerName;
    private String customerTel;
    private String empName;
    private Integer followStatus;
    private Integer pageNo;
    private Integer pageSize;
    private Integer userId;
}
