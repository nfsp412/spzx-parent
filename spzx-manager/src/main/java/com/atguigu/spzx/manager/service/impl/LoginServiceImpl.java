package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.constant.RedisConstant;
import com.atguigu.spzx.common.exception.LoginException;
import com.atguigu.spzx.common.result.ResultCodeEnum;
import com.atguigu.spzx.common.utils.JwtUtil;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.LoginService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.enums.BaseStatus;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.wf.captcha.SpecCaptcha;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {
        String captcha = loginDto.getCaptcha();
        if (captcha == null || Objects.equals("", captcha)) {
            //验证码为空
            throw new LoginException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        String key = loginDto.getCodeKey();
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null) {
            //验证码已过期
            throw new LoginException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if (!Objects.equals(value, captcha.toLowerCase())) {
            //验证码不匹配
            throw new LoginException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }
        //查询用户
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, loginDto.getUserName());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser == null) {
            //用户不存在
            throw new LoginException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (sysUser.getStatus() == BaseStatus.DISABLE) {
            //用户被禁用
            throw new LoginException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }


        if (!Objects.equals(sysUser.getPassword(), DigestUtils.md5Hex(loginDto.getPassword()))) {
            //密码错误
            throw new LoginException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        return new LoginVo(JwtUtil.createToken(sysUser.getId(), sysUser.getUsername()), null);
    }


    @Override
    public ValidateCodeVo generateValidateCode() {
        //生成验证码图片
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        //get code
        String code = specCaptcha.text().toLowerCase();
        //get image
        String image = specCaptcha.toBase64();
        //存入redis
        //key: admin:login:UUID
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        log.info("登录验证码对应redis key：" + key);
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new ValidateCodeVo(key, image);
    }
}

