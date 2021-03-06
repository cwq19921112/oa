package com.chenwuqiang.oa.service.impl;

import com.chenwuqiang.oa.dto.DelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.example.AccountExample;
import com.chenwuqiang.oa.mapper.AccountMapper;
import com.chenwuqiang.oa.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private FastFileStorageClient fc;

    @Value("${oa.uploadPath}")
    private String uploadPath;

    @Override
    public Account validataAccount(String loginName, String password) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return null;
        }
        Account condition = new Account();
        condition.setUserName(loginName);
        condition.setPassword(password);
        Account account = accountMapper.selectByPermission(condition);
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
    public int delete(DelReqDto reqDto) {
        return accountMapper.deleteByPrimaryKey(reqDto.getId());
    }

    @Override
    public Account findById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editAccount(HttpServletRequest request, Account account, MultipartFile filename) throws Exception{
        if (filename != null && !StringUtils.isEmpty(filename.getOriginalFilename())) {
            // 元数据
            Set<MetaData> metaDataSet = new HashSet<MetaData>();
            metaDataSet.add(new MetaData("CreateDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            FastFile fastFile = new FastFile(filename.getInputStream(), filename.getSize(), FilenameUtils.getExtension(filename.getOriginalFilename()), metaDataSet);
            String uploadFile = fc.uploadFile(fastFile).getFullPath();

            account.setHeadImgPath(uploadFile);
            // 查用户
            Account existAccount = accountMapper.selectByPrimaryKey(account.getId());
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            if (Objects.equals(existAccount, sessionAccount)) {
                // 更新session
                sessionAccount.setHeadImgPath(account.getHeadImgPath());
                request.getSession().setAttribute("account", sessionAccount);
            }

            String headImgPath = existAccount.getHeadImgPath();
            if (existAccount!=null && !StringUtils.isEmpty(headImgPath)) {
                if (!headImgPath.contains("group")) {
                    headImgPath = "/group1" + headImgPath;
                }
                try {
                    // 删除旧的头像文件
                    fc.deleteFile(headImgPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        accountMapper.updateByPrimaryKeySelective(account);
    }
}
