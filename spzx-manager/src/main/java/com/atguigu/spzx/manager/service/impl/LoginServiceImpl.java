package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.constant.RedisConstant;
import com.atguigu.spzx.manager.service.LoginService;
import com.atguigu.spzx.model.vo.ValidateCodeVo;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        //生成验证码
        //easy-captcha或者hutool,hutool要额外添加data:image/png;base64,到图片字符串
        //生成验证码图片
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        //获取验证码里面的code
        String code = specCaptcha.text().toLowerCase();
        //获取验证码图片
        String image = specCaptcha.toBase64();
        //获取key
        String key = RedisConstant.ADMIN_LOGIN_PREFIX
                + UUID.randomUUID().toString().replace("-", "");

        //存储redis
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        log.info("验证码redis的key: " + key);
        //返回vo类
        return new ValidateCodeVo(key, image);
    }
}

