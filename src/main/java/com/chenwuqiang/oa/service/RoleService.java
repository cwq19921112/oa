package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

    PageInfo<Role> findPage(Integer pageNum, Integer pageSize);
}