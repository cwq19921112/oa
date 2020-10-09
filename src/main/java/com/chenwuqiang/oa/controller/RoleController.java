package com.chenwuqiang.oa.controller;

import com.chenwuqiang.oa.dto.DelReqDto;
import com.chenwuqiang.oa.dto.RspDto;
import com.chenwuqiang.oa.entity.Permission;
import com.chenwuqiang.oa.entity.Role;
import com.chenwuqiang.oa.service.PermissionService;
import com.chenwuqiang.oa.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(required = false, defaultValue = "5") Integer pageSize, Model model) {
        PageInfo<Role> pageInfo = roleService.findPage(pageNum, pageSize);
        model.addAttribute("roleList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

        return "/role/list";
    }

    @RequestMapping("/add")
    public String add(@RequestParam(required = false, name = "id") Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        List<Permission> permissions = permissionService.selectAll();
        model.addAttribute("permissions", permissions);

        return "/role/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public RspDto modifyPost(@RequestParam int[] permissions,
                             @RequestParam int id,
                             @RequestParam String name) {
        System.out.println(Arrays.toString(permissions));
//        roleService.add(role);
        return RspDto.buildSuccess();
    }

    @PostMapping("/delete")
    @ResponseBody
    public RspDto registerPost(@RequestBody DelReqDto reqDto) {
        int count = roleService.delete(reqDto);
        if (count > 0) {
            return RspDto.buildSuccess();
        } else {
            return RspDto.buildFail("删除失败");
        }
    }
}
