package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.checkerframework.framework.qual.TargetLocations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色相关接口")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/findByPage/{current}/{size}")
    @Operation(summary = "分页获取角色明细")
    public Result<IPage<SysRole>> findByPage(@PathVariable("current") Integer current,
                                             @PathVariable("size") Integer size,
                                             @RequestBody SysRoleDto sysRoleDto) {
        System.out.println(sysRoleDto);
        LoginUser user = LoginUserHolder.getLoginUser();
        Page<SysRole> page = new Page<>(current, size);
        IPage<SysRole> sysRoleIPage = sysRoleService.getSysRolePage(page,sysRoleDto);
        return Result.ok(sysRoleIPage);
    }

    @PostMapping("/saveSysRole")
    @Operation(summary = "添加角色信息接口")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        System.out.println("=========================="+sysRole);
        sysRoleService.save(sysRole);
        return Result.ok(null);
    }

    @PutMapping("/updateSysRole")
    @Operation(summary = "修改角色信息接口")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        System.out.println(sysRole);
        sysRoleService.updateById(sysRole);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteById/{roleId}")
    @Operation(summary = "删除角色信息接口")
    public Result deleteById(@PathVariable("roleId") Integer id){
        sysRoleService.removeById(id);
        return Result.ok(null);
    }

    @GetMapping("/findAllRoles/{userId}")
    @Operation(summary = "根据用户id获取所有角色接口")
    public Result<Map<String, Object>> findAllRoles(@PathVariable("userId") Integer id) {
        Map<String, Object> map = sysRoleService.findAllRoles(id);
        return Result.ok(map);
    }
}
