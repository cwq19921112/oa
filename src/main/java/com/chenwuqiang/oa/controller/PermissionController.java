package com.chenwuqiang.oa.controller;

import com.chenwuqiang.oa.entity.Permission;
import com.chenwuqiang.oa.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        PageInfo<Permission> pageInfo = permissionService.findPage(pageNum, pageSize);
        model.addAttribute("permissionList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

        return "/permission/list";
    }
}
