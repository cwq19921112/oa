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
        example.setOrderByClause("id");
        List<Role> accountList = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.selectRolePermissions(id);
    }

    @Override
    public void add(Role role, int[] permissions) {
        Integer id = role.getId();
        if (id == null) {
            // 新增
            roleMapper.insertSelective(role);
            id = role.getId();
        } else {
            // 编辑
            roleMapper.updateByPrimaryKeySelective(role);
            // 删除role_permission
            roleMapper.deleteRolePermission(id);
        }
        if (permissions != null && permissions.length > 0) {
            // 批量插入role_permission
            roleMapper.insertRolePermissions(id, permissions);
        }
    }

    @Override
    public int delete(DelReqDto reqDto) {
        Integer id = reqDto.getId();
        roleMapper.deleteRolePermission(id);
        return roleMapper.deleteByPrimaryKey(id);
    }
}
