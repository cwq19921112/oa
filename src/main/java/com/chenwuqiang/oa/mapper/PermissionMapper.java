package com.chenwuqiang.oa.mapper;

import com.chenwuqiang.oa.entity.Permission;
import com.chenwuqiang.oa.example.PermissionExample;
import org.springframework.stereotype.Repository;

/**
 * PermissionMapper继承基类
 */
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}