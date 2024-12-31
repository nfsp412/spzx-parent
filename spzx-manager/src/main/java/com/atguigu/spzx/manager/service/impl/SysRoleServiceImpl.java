package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.model.dto.AssignMenuDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.SysRole;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nfsp4
 * @description 针对表【sys_role(角色)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<SysRole> findByPage(Page<SysRole> page, String roleName) {
        return sysRoleMapper.findByPage(page, roleName);
    }

//    @Override
//    public void saveSysRole(SysRole sysRole) {
//        super.save(sysRole);
//    }

    @Override
    public void deleteById(Integer id) {
        sysRoleMapper.deleteById(id);
    }

    @Override
    public void saveOrUpdateById(SysRole sysRole) {

        super.saveOrUpdate(sysRole);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //1
        List<SysRole> sysRoleList = super.list();

        //2根据userId查询roleId的List
        List<Long> sysUserRoles = sysRoleMapper.findSysUserRoleIdsByUserId(userId);

        //3封装map
        HashMap<String, Object> map = new HashMap<>();
        map.put("allRolesList", sysRoleList);
        map.put("sysUserRoles", sysUserRoles);
        return map;
    }

    @Transactional
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        //先删除
        sysRoleMapper.deleteSysRoleMenuByRoleId(assignMenuDto.getRoleId());
        List<Map<String, Number>> list = assignMenuDto.getMenuIdList();
        if (list != null && list.size() > 0) {
            sysRoleMapper.doAssign(assignMenuDto.getRoleId(),assignMenuDto.getMenuIdList());
        }

    }
}
