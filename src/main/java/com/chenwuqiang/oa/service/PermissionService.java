package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.entity.Permission;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PermissionService {
    PageInfo<Permission> findPage(Integer pageNum, Integer pageSize);

    Permission selectById(Integer id);

    void modify(Permission permission);

    List<Permission> selectAll();
}
