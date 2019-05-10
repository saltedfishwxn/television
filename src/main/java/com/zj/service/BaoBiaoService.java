package com.zj.service;

import java.util.Map;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-04-15 16:53
 */
public interface BaoBiaoService {
    /**
     * 查询所有产品信息
     * @param query
     * @return
     */
    Map findAll(Map<String, Object> query);

}
