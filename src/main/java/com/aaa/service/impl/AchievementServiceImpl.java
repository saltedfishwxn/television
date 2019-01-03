package com.aaa.service.impl;

import com.aaa.dao.AchievementMapper;
import com.aaa.service.IAchievementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AchievementServiceImpl
 * @Description 按条件对时间段内业绩查询
 * @Author LP
 * @Date 2018/12/28 11:17
 * @Version 1.0
 **/
@Service
public class AchievementServiceImpl implements IAchievementService {
    @Autowired
    private AchievementMapper achievementMapper;

    /**
     * 按条件对时间段内业绩查询
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> statistics(Map<String, Object> query) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageNo = (Integer) query.get("pageNo");
        int pageSize = (Integer) query.get("pageSize");
        PageHelper.startPage(pageNo,pageSize);
        map.put("rowList",achievementMapper.listAchievement(query));
        map.put("totalList",achievementMapper.countAchievement(query));
        return map;
    }
}
