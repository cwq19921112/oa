package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.dto.DelReqDto;
import com.chenwuqiang.oa.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

    PageInfo<Role> findPage(Integer pageNum, Integer pageSize);

    Role findById(Integer id);

    void add(Role role);

    int delete(DelReqDto reqDto);
}
