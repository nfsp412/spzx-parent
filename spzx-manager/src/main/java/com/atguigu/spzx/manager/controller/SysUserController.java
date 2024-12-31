package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.anno.Log;
import com.atguigu.spzx.common.result.Result;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.AssignRoleDto;
import com.atguigu.spzx.model.dto.SysUserDto;

import com.atguigu.spzx.model.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/admin/system/sysUser")
@RestController
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @Log(title = "用户管理分页查询",businessType = 0)
    @GetMapping("/findByPage/{current}/{size}")
    public Result<Page<SysUser>> findByPage(
            @PathVariable("current") Integer current,
            @PathVariable("size") Integer size,
            SysUserDto sysUserDto
    ) {
//        System.out.println(1/0);
        Page<SysUser> page = new Page<>(current, size);
        Page<SysUser> sysUserPage = sysUserService.findByPage(page, sysUserDto);
        return Result.ok(sysUserPage);
    }

//    @PostMapping("/saveSysUser")
//    public Result saveSysUser(@RequestBody SysUser sysUser) {
//
//        sysUserService.saveSysUser(sysUser);
//        return Result.ok(null);
//    }

    @PostMapping("/saveOrUpdateSysUser")
    public Result saveOrUpdateSysUser(@RequestBody SysUser sysUser) {

        sysUserService.saveOrUpdateSysUser(sysUser);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteSysUserById/{id}")
    public Result deleteSysUserById(@PathVariable("id") Integer id) {

        sysUserService.deleteSysUserById(id);
        return Result.ok(null);
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleDto assignRoleDto ) {


        sysUserService.doAssign(assignRoleDto);
        return Result.ok(null);
    }
}
