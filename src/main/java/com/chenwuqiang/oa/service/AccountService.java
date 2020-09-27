package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.dto.AccountDelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.github.pagehelper.PageInfo;

public interface AccountService {
    Account validataAccount(String loginName, String password);

    PageInfo<Account> findPage(Integer pageNum, Integer pageSize);

    RspDto register(Account account);

    int delete(AccountDelReqDto reqDto);
}
