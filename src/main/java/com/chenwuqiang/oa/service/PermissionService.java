package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.entity.Permission;
import com.github.pagehelper.PageInfo;

public interface PermissionService {
    PageInfo<Permission> findPage(Integer pageNum, Integer pageSize);
}
