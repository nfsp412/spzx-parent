package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.SysUserException;
import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.result.ResultCodeEnum;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser test(String username) {
        return sysUserMapper.test(username);
    }


    @Override
    public UserInfoVo getUserInfoVo(LoginUser user) {
        SysUser sysUser = sysUserMapper.selectById(user.getUserId());
        log.info("登录用户信息：" + user);
        return new UserInfoVo(sysUser.getName(), sysUser.getAvatar());
    }

    @Override
    public IPage<SysUser> findByPage(Page<SysUser> page, SysUserDto sysUserDto) {
        return sysUserMapper.findByPage(page, sysUserDto);
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //用户名不能重复
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, sysUser.getUsername());
        Long count = sysUserMapper.selectCount(wrapper);
        if (count > 0) {
            throw new SysUserException(ResultCodeEnum.ADMIN_USERNAME_EXISTS);
        }
        //密码加密
        sysUser.setPassword(DigestUtils.md5Hex(sysUser.getPassword()));
        //
        sysUserMapper.insert(sysUser);
    }

}

