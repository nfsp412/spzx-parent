package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.mapper.SysRoleUserMapper;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public IPage<SysRole> getSysRolePage(Page<SysRole> page, SysRoleDto sysRoleDto) {
        return sysRoleMapper.getSysRolePage(page, sysRoleDto);
    }

    @Override
    public Map<String, Object> findAllRoles(Integer id) {
        //查询用户现有角色
        List<Long> sysUserRoles = sysRoleUserMapper.findAllRoles(id);
        //查询所有角色
        List<SysRole> allRolesList = super.list();
        HashMap<String, Object> map = new HashMap<>();
        map.put("sysUserRoles",sysUserRoles);
        map.put("allRolesList",allRolesList);
        return map;
    }
}
