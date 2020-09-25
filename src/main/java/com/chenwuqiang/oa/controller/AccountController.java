package com.chenwuqiang.oa.controller;

import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/login")
    public String login() {

        return "account/login";
    }

    @PostMapping("/validataAccount")
    @ResponseBody
    public String validataAccount(@RequestParam(name = "loginName", required = false) String loginName,
                                  @RequestParam(name = "password", required = false) String password,
                                  HttpServletRequest request) {
        Account account = accountService.validataAccount(loginName, password);
        if (account == null) {
            return "登录失败";
        }
        request.getSession().setAttribute("account", account);
        return "success";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "account/login";
    }

    @RequestMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        List<Account> list = accountService.findPage(pageNum, pageSize);
        model.addAttribute("accountList", list);

        return "/account/list";
    }

    @GetMapping("/register")
    public String register() {
        return "/account/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public RspDto registerPost(@RequestBody Account account) {
        RspDto rspDto = accountService.register(account);
        return rspDto;
    }

    @RequestMapping("/reg-success")
    public String regSuccess() {

        return "/account/reg-success";
    }
}
