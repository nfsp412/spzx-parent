package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.manager.service.LoginService;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin/system/index")
@Tag(name = "主页相关接口")
//@CrossOrigin(allowCredentials = "true",originPatterns = "*",allowedHeaders = "*")
public class IndexController {

    @Resource
    private LoginService loginService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping(value = "/login")
    @Operation(summary = "登录接口")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = loginService.login(loginDto);
        return Result.ok(loginVo);
    }

    @GetMapping("/test")
    @Operation(summary = "测试接口")
    public Result<SysUser> test(@RequestParam("username") String username) {
        SysUser sysUser = sysUserService.test(username);
        return Result.ok(sysUser);
    }

    @GetMapping("/generateValidateCode")
    @Operation(summary = "获取验证码接口")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = loginService.generateValidateCode();
        return Result.ok(validateCodeVo);
    }

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户信息接口")
    public Result<UserInfoVo> getUserInfo() {

        LoginUser user = LoginUserHolder.getLoginUser();
        UserInfoVo userInfoVo = sysUserService.getUserInfoVo(user);
        return Result.ok(userInfoVo);
    }

    @GetMapping("/menus")
    @Operation(summary = "获取主页面信息接口")
    public Result<List<SysMenuVo>> getMenus() {
        List<SysMenuVo> sysMenuVoList = sysMenuService.getSysMenuVo();
        return Result.ok(sysMenuVoList);
    }

    @GetMapping("/logout")
    @Operation(summary = "退出登录接口")
    public Result logout() {
        return Result.ok(null);
    }
}
