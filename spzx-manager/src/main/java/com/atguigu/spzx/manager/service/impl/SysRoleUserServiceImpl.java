package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleUserMapper;
import com.atguigu.spzx.manager.service.SysRoleUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.entity.system.SysRoleUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    @Transactional
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //删除
        LambdaQueryWrapper<SysRoleUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleUser::getUserId, assginRoleDto.getUserId());
        super.remove(wrapper);
        //插入
        List<SysRoleUser> list = assginRoleDto.getRoleIdList()
                .stream()
                .map(roleId -> new SysRoleUser(roleId, assginRoleDto.getUserId()))
                .toList();
        super.saveBatch(list);
    }
}
