package com.zj.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-04-15 16:55
 */
@Mapper
public interface BaoBiaoMapper {
    /**
     * 分页查询所有订单信息
     * @param
     * @return
     */
    List<Map> findAllByPage();

    /**
     * 查询总订单数量
     * @return
     * @param
     */
    int getTotal();
}
