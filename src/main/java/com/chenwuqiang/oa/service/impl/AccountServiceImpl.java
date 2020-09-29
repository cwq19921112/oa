package com.chenwuqiang.oa.service.impl;

import com.chenwuqiang.oa.dto.AccountDelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.example.AccountExample;
import com.chenwuqiang.oa.mapper.AccountMapper;
import com.chenwuqiang.oa.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Value("${oa.uploadPath}")
    private String uploadPath;

    @Override
    public Account validataAccount(String loginName, String password) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return null;
        }
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUserNameEqualTo(loginName).andPasswordEqualTo(password);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if (accountList.size() != 1) {
            return null;
        }
        Account account = accountList.get(0);
        return account;
    }

    @Override
    public PageInfo<Account> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        AccountExample example = new AccountExample();
        List<Account> accountList = accountMapper.selectByExample(example);
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }

    @Override
    public RspDto register(Account account) {
        AccountExample example = new AccountExample();
        example.createCriteria().andUserNameEqualTo(account.getUserName());
        List<Account> accountList = accountMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(accountList)) {
            return RspDto.buildFail("该用户名已被注册");
        }
        int insert = accountMapper.insert(account);
        if (insert == 0) {
            return RspDto.buildFail("注册用户失败");
        }
        return RspDto.buildSuccess();
    }

    @Override
    public int delete(AccountDelReqDto reqDto) {
        return accountMapper.deleteByPrimaryKey(reqDto.getId());
    }

    @Override
    public Account findById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editAccount(HttpServletRequest request, Account account, MultipartFile filename) throws Exception{
        if (filename != null) {
            String fileName = UUID.randomUUID() + filename.getOriginalFilename();
            String path = uploadPath + fileName;
            Files.write(new File(path).toPath(), filename.getBytes());
            account.setHeadImgPath("/" + fileName);
            
            // 查用户
            Account existAccount = accountMapper.selectByPrimaryKey(account.getId());
            if (existAccount!=null && !StringUtils.isEmpty(existAccount.getHeadImgPath())) {
                // 删除旧的头像文件
                new File(uploadPath + "/" + existAccount.getHeadImgPath()).delete();
            }
            // 更新session
            request.getSession().setAttribute("account", account);
        }
        accountMapper.updateByPrimaryKeySelective(account);
    }
}
