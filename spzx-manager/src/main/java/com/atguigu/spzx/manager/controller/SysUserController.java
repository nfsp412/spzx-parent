package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.SysRoleUserService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/admin/system/sysUser")
@RestController
@Tag(name = "用户相关接口")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleUserService sysRoleUserService;

    @GetMapping("/findByPage/{current}/{size}")
    @Operation(summary = "分页查询用户信息接口")
    public Result<IPage<SysUser>> findByPage(@PathVariable("current") Integer current,
                                             @PathVariable("size") Integer size,
                                             SysUserDto sysUserDto) {
        Page<SysUser> page = new Page<>(current,size);
        IPage<SysUser> sysUserList = sysUserService.findByPage(page,sysUserDto);
        return Result.ok(sysUserList);
    }

    @DeleteMapping("/deleteById/{userId}")
    @Operation(summary = "删除用户信息接口")
    public Result deleteSysUser(@PathVariable("userId") Integer id){
        sysUserService.removeById(id);
        return Result.ok(null);
    }

    @PostMapping("/saveSysUser")
    @Operation(summary = "添加用户信息接口")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        System.out.println(sysUser);
        sysUserService.saveSysUser(sysUser);
        return Result.ok(null);
    }

    @PutMapping("/updateSysUser")
    @Operation(summary = "修改用户信息接口")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.ok(null);
    }

    @PostMapping("/doAssign")
    @Operation(summary = "用户分配角色接口")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysRoleUserService.doAssign(assginRoleDto);
        return Result.ok(null);
    }
}
