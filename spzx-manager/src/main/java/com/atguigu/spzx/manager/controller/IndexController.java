package com.atguigu.spzx.manager.controller;

import cn.hutool.log.Log;
import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;

import com.atguigu.spzx.common.result.ResultCodeEnum;
import com.atguigu.spzx.manager.service.LoginService;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.LoginDto;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.model.entity.SysUser;
import com.atguigu.spzx.model.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/admin/system/index")
@Tag(name = "动态菜单")
public class IndexController {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private LoginService loginService;

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.ok(loginVo);
    }

    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = loginService.generateValidateCode();
        return Result.ok(validateCodeVo);
    }

    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo() {
        LoginUser user = LoginUserHolder.getLoginUser();
        SysUser sysUser = sysUserService.getUserInfo(user);
        return Result.ok(sysUser);
    }

    @GetMapping("/logout")
    public Result logout() {
        return Result.ok(null);
    }

    @GetMapping("/menus")
    @Operation(summary = "menus")
    public Result<List<SysMenuVo>> menus() {
        List<SysMenuVo> list = sysMenuService.menus();

        return Result.ok(list);
    }

}
