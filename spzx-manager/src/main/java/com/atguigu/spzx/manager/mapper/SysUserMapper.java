package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.AssignRoleDto;
import com.atguigu.spzx.model.dto.SysUserDto;
import com.atguigu.spzx.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Page<SysUser> findByPage(Page<SysUser> page, SysUserDto sysUserDto);

    void doAssign(Long userId, List<Long> roleIdList);

    void deleteSysUserRoleByUserId(Long userId);
}




