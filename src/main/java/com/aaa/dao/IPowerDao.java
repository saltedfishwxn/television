package com.aaa.dao;

import com.aaa.entity.Node;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName IPowerDao
 * @Description TODO
 * @Author LP
 * @Date 2018/12/15 10:37
 * @Version 1.0
 **/
@Mapper
public interface IPowerDao {

    List<Node> getList();
}
