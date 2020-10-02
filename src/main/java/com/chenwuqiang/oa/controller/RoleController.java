package com.chenwuqiang.oa.controller;

import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.service.AccountService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        PageInfo<Account> pageInfo = accountService.findPage(pageNum, pageSize);
        model.addAttribute("accountList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

        return "/role/list";
    }
}
