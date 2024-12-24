package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysRoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    List<Long> findAllRoles(Integer id);
}
