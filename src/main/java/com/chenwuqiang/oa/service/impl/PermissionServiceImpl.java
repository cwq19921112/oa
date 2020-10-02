package com.chenwuqiang.oa.service.impl;

import com.chenwuqiang.oa.entity.Permission;
import com.chenwuqiang.oa.example.PermissionExample;
import com.chenwuqiang.oa.mapper.PermissionMapper;
import com.chenwuqiang.oa.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageInfo<Permission> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PermissionExample example = new PermissionExample();
        List<Permission> accountList = permissionMapper.selectByExample(example);
        PageInfo<Permission> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }
}
