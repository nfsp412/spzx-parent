package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.SysRole;
import com.atguigu.spzx.model.vo.SysRoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author nfsp4
 * @description 针对表【sys_role(角色)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.SysRole
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    Page<SysRole> findByPage(Page<SysRole> page, String roleName);


    List<Long> findSysUserRoleIdsByUserId(Long userId);

    void deleteSysRoleMenuByRoleId(Long roleId);

    void doAssign(Long roleId, List<Map<String, Number>> menuIdList);
}




