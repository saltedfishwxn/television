package com.aaa.dao;

import com.aaa.entity.Resource;

public interface ResourceMapper {
    int insert(Resource record);

    int insertSelective(Resource record);
}