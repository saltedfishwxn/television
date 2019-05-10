package com.zj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zj.dao.BaoBiaoMapper;
import com.zj.service.BaoBiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-04-15 16:54
 */
@Service
public class BaoBiaoServiceImpl implements BaoBiaoService {
    @Autowired
    private BaoBiaoMapper baoBiaoMapper;
    /**
     * 查询所有产品信息
     * @param query
     * @return
     */
    @Override
    public Map findAll(Map<String, Object> query) {
        int pageNo=Integer.parseInt(query.get("pageNo")+"");
        int pageSize=Integer.parseInt(query.get("pageSize")+"");
        //Map amp =(Map)query.get("form");
        PageHelper.startPage(pageNo,pageSize);
        Map map=new HashMap();
        map.put("rowList",baoBiaoMapper.findAllByPage());
        map.put("getTotal",baoBiaoMapper.getTotal());
        return map;
    }
}
