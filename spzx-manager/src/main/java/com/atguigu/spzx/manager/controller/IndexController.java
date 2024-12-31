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
    public Result<List<DynamicMenusVo>> menus() {
        List<DynamicMenusVo> list = sysMenuService.findDynamicMenus();

//        DynamicMenusVo s1 = new DynamicMenusVo();
//        s1.setName("system");
//        s1.setTitle("系统管理");
//        s1.setChildren(null);
//
//        DynamicMenusVo s2 = new DynamicMenusVo();
//        s2.setName("base");
//        s2.setTitle("基础数据管理");
//
//        ArrayList<DynamicMenusVo> temp = new ArrayList<>();
//        DynamicMenusVo ss2 = new DynamicMenusVo();
//        ss2.setTitle("地区管理");
//        ss2.setName("region");
//        temp.add(ss2);
//        s2.setChildren(temp);
//
//        ArrayList<DynamicMenusVo> list = new ArrayList<>();
//        list.add(s1);
//        list.add(s2);

//        System.out.println(list);

        return Result.ok(list);
    }

    // com.atguigu.spzx.system.controller#IndexController
//    @GetMapping("/menus")
//    @Operation(summary = "menus")
//    public Result menus() {
//        List<SysMenuVo2> sysMenuVoList =  sysMenuService.findUserMenuList() ;
//        System.out.println(sysMenuVoList);
//        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
//    }
}
