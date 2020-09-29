package com.chenwuqiang.oa.service;

import com.chenwuqiang.oa.dto.AccountDelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AccountService {
    Account validataAccount(String loginName, String password);

    PageInfo<Account> findPage(Integer pageNum, Integer pageSize);

    RspDto register(Account account);

    int delete(AccountDelReqDto reqDto);

    Account findById(Integer id);

    void editAccount(HttpServletRequest request, Account account, MultipartFile filename) throws Exception;
}
