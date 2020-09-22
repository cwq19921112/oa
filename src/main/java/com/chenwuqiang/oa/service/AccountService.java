package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.entity.Account;

import java.util.List;

public interface AccountService {
    Account validataAccount(String loginName, String password);

    List<Account> findPage(Integer pageNum, Integer pageSize);
}
