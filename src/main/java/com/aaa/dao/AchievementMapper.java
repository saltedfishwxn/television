package com.aaa.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 业绩查询接口
 */
@Mapper
public interface AchievementMapper {
    /**
     * 按条件对时间段内业绩查询
     * @param query
     * @return
     */
    List<Map<String,Object>> listAchievement(Map<String, Object> query);

    /**
     * 查询数据条数
     * @param query
     * @return
     */
    Integer countAchievement(Map<String, Object> query);
}
