package com.chenwuqiang.oa.service.impl;

import com.chenwuqiang.oa.dto.DelReqDto;
import com.chenwuqiang.oa.entity.Role;
import com.chenwuqiang.oa.example.RoleExample;
import com.chenwuqiang.oa.mapper.RoleMapper;
import com.chenwuqiang.oa.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample example = new RoleExample();
        List<Role> accountList = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Role role) {
        Integer id = role.getId();
        if (id == null) {
            // 新增
            roleMapper.insertSelective(role);
        } else {
            // 编辑
            roleMapper.updateByPrimaryKeySelective(role);
        }
    }

    @Override
    public int delete(DelReqDto reqDto) {
        return roleMapper.deleteByPrimaryKey(reqDto.getId());
    }
}
