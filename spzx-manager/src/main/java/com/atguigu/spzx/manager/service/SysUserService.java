package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysUserService extends IService<SysUser> {
    SysUser test(String username);

    UserInfoVo getUserInfoVo(LoginUser user);

    IPage<SysUser> findByPage(Page<SysUser> page, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

}
