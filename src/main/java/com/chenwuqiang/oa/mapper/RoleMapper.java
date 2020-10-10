package com.chenwuqiang.oa.mapper;

import com.chenwuqiang.oa.entity.Role;
import com.chenwuqiang.oa.example.RoleExample;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper继承基类
 */
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {
    void deleteRolePermission(Integer id);

    void insertRolePermissions(Integer id, int[] permissions);

    Role selectRolePermissions(Integer id);
}