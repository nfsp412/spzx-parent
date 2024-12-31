package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.atguigu.spzx.common.anno.Log;
import com.atguigu.spzx.common.exception.LoginException;
import com.atguigu.spzx.common.exception.SysUserException;
import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.result.ResultCodeEnum;
import com.atguigu.spzx.common.utils.JwtUtil;
import com.atguigu.spzx.model.dto.AssignRoleDto;
import com.atguigu.spzx.model.dto.LoginDto;
import com.atguigu.spzx.model.dto.SysUserDto;
import com.atguigu.spzx.model.vo.LoginVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.SysUser;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import kotlin.jvm.internal.Lambda;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author nfsp4
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        //非空校验?
        if (loginDto == null) {
            throw new LoginException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        //校验验证码
        String captcha = loginDto.getCaptcha();
        String key = loginDto.getKey();
        if (captcha == null || key == null) {
            //非空校验
            throw new LoginException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        String redisValue = stringRedisTemplate.opsForValue().get(key);
        if (redisValue == null) {
            throw new LoginException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if (!Objects.equals(redisValue, captcha)) {
            throw new LoginException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //校验用户名
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, loginDto.getUsername());
        List<SysUser> sysUserList = sysUserMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(sysUserList)) {
            //用户不存在
            throw new LoginException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (sysUserList.size() > 1) {
            //用户名重复?应该在注册时校验
            throw new LoginException(ResultCodeEnum.ADMIN_USERNAME_EXISTS);
        }
        SysUser sysUser = sysUserList.get(0);
        //校验密码
        if (!Objects.equals(
                sysUser.getPassword(),
                DigestUtils.md5Hex(loginDto.getPassword())
        )) {
            //密码错误
            throw new LoginException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        //生成token
        String token = JwtUtil.createToken(sysUser.getId(), sysUser.getUsername());
        //包装返回
        return new LoginVo(token, "");
    }

    @Override
    public SysUser getUserInfo(LoginUser user) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId, user.getUserId());
        wrapper.select(
                SysUser::getId,
                SysUser::getName,
                SysUser::getAvatar);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public Page<SysUser> findByPage(Page<SysUser> page, SysUserDto sysUserDto) {
        return sysUserMapper.findByPage(page, sysUserDto);
    }

//    @Override
//    public void saveSysUser(SysUser sysUser) {
//        log.info("新增用户信息: " + sysUser);
//        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SysUser::getUsername, sysUser.getUsername());
//        Long count = sysUserMapper.selectCount(wrapper);
//        if (count != 0) {
//            //username已经存在
//            throw new SysUserException(ResultCodeEnum.ADMIN_USERNAME_EXISTS);
//        }
//        //密码md5加密
//        String pwd = DigestUtils.md5Hex(sysUser.getPassword());
//        sysUser.setPassword(pwd);
//        sysUserMapper.insert(sysUser);
//    }

    @Override
    public void saveOrUpdateSysUser(SysUser sysUser) {
        if (sysUser.getId() == null) {
            //新增
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUser::getUsername, sysUser.getUsername());
            Long count = sysUserMapper.selectCount(wrapper);

            if (count != 0) {
                throw new SysUserException(ResultCodeEnum.ADMIN_USERNAME_EXISTS);
            }
        } else {
            //修改
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysUser::getUsername, sysUser.getUsername());
            Long count = sysUserMapper.selectCount(wrapper);

            LambdaQueryWrapper<SysUser> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(SysUser::getId, sysUser.getId());
            SysUser selectOne = sysUserMapper.selectOne(wrapper1);

            if (!Objects.equals(selectOne.getUsername(), sysUser.getUsername()) && count != 0) {
                throw new SysUserException(ResultCodeEnum.ADMIN_USERNAME_EXISTS);
            }
        }

        //密码md5加密
        String pwd = DigestUtils.md5Hex(sysUser.getPassword());
        sysUser.setPassword(pwd);
        super.saveOrUpdate(sysUser);

    }

    @Override
    public void deleteSysUserById(Integer id) {
        sysUserMapper.deleteById(id);
    }

    @Log(title = "用户分配角色", businessType = 2)
    @Transactional
    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {
        //先删除
        sysUserMapper.deleteSysUserRoleByUserId(assignRoleDto.getUserId());
//        System.out.println(1 / 0);
        List<Long> list = assignRoleDto.getRoleIdList();
        if (list != null && list.size() > 0) {
            sysUserMapper.doAssign(assignRoleDto.getUserId(), assignRoleDto.getRoleIdList());
        }
    }
}
