package com.chenwuqiang.oa.mapper;

import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.example.AccountExample;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
}