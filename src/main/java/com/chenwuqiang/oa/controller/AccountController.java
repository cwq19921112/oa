package com.chenwuqiang.oa.controller;

import com.chenwuqiang.oa.dto.AccountDelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.service.AccountService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        PageInfo<Account> pageInfo = accountService.findPage(pageNum, pageSize);
        model.addAttribute("accountList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

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

    @PostMapping("/delete")
    @ResponseBody
    public RspDto registerPost(@RequestBody AccountDelReqDto reqDto) {
        int count = accountService.delete(reqDto);
        if (count > 0) {
            return RspDto.buildSuccess();
        } else {
            return RspDto.buildFail("删除失败");
        }
    }

    @GetMapping("/account-edit")
    public String info(@RequestParam(name = "id") Integer id, Model model) {
        Account account = accountService.findById(id);
        model.addAttribute("account", account);
        return "/account/account-edit";
    }

    @PostMapping("/account-edit")
    public String info(MultipartFile filename, Account account) {
        accountService.editAccount(account);
        return "redirect:/account/list";
    }
}
