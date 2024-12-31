package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.AssignMenuDto;
import com.atguigu.spzx.model.entity.SysRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author nfsp4
 * @description 针对表【sys_role(角色)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface SysRoleService extends IService<SysRole> {

    Page<SysRole> findByPage(Page<SysRole> page, String roleName);

//    void saveSysRole(SysRole sysRole);

    void deleteById(Integer id);

    void saveOrUpdateById(SysRole sysRole);

    Map<String, Object> findAllRoles(Long userId);

    void doAssign(AssignMenuDto assignMenuDto);
}
