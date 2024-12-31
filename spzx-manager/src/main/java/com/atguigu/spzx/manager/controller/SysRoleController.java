package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.AssignMenuDto;
import com.atguigu.spzx.model.dto.AssignRoleDto;
import com.atguigu.spzx.model.dto.SysRoleDto;

import com.atguigu.spzx.model.entity.SysRole;
import com.atguigu.spzx.model.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping("/findByPage/{current}/{size}")
    public Result<Page<SysRole>> findByPage(
            @PathVariable("current") Integer current,
            @PathVariable("size") Integer size,
            @RequestParam(value = "roleName", required = false) String roleName
    ) {
        Page<SysRole> page = new Page<>(current, size);
        Page<SysRole> sysRolePage = sysRoleService.findByPage(page, roleName);
        return Result.ok(sysRolePage);
    }

//    @PostMapping("/saveSysRole")
//    public Result saveSysRole(@RequestBody SysRole sysRole) {
//        sysRoleService.saveSysRole(sysRole);
//        return Result.ok(null);
//    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        sysRoleService.deleteById(id);
        return Result.ok(null);
    }

    @PostMapping("/saveOrUpdateById")
    public Result saveOrUpdateById(@RequestBody SysRole sysRole) {

        sysRoleService.saveOrUpdateById(sysRole);
        return Result.ok(null);
    }

    /**
     * Map封装两个kv
     * "allRolesList" => List<SysRole>
     *
     * @return
     */
    @GetMapping("/findAllRoles/{id}")
    public Result<Map<String, Object>> findAllRoles(@PathVariable("id") Long userId) {
        Map<String, Object> map = sysRoleService.findAllRoles(userId);
        return Result.ok(map);
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto ) {
        System.out.println("=========================>"+assignMenuDto);
        sysRoleService.doAssign(assignMenuDto);
        return Result.ok(null);
    }
}
