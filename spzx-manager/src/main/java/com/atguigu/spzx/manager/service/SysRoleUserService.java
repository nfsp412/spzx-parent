package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.entity.system.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysRoleUserService extends IService<SysRoleUser> {

    void doAssign(AssignRoleDto assignRoleDto);
}
