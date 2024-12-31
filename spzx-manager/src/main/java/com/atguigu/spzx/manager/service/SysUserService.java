package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.model.dto.AssignRoleDto;
import com.atguigu.spzx.model.dto.LoginDto;
import com.atguigu.spzx.model.dto.SysUserDto;
import com.atguigu.spzx.model.entity.SysUser;
import com.atguigu.spzx.model.vo.LoginVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author nfsp4
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface SysUserService extends IService<SysUser> {

    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(LoginUser user);

    Page<SysUser> findByPage(Page<SysUser> page, SysUserDto sysUserDto);

//    void saveSysUser(SysUser sysUser);

    void saveOrUpdateSysUser(SysUser sysUser);

    void deleteSysUserById(Integer id);

    void doAssign(AssignRoleDto assignRoleDto);
}
