package com.aaa.service;

import com.aaa.entity.Tree;

import java.util.List;

/**
 * @Author: 尚冠峰
 * @Date: 2018/12/15 0015 15:45
 * @Version 1.0
 */
public interface ResourceBiz {
    List<Tree> showAllResources(String username);
}
