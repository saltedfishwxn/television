package com.aaa.service;

import java.util.Map;

public interface IAchievementService {
    /**
     * 按条件对时间段内业绩查询
     * @param query
     * @return
     */
    Map<String, Object> statistics(Map<String, Object> query);
}
