package com.atguigu.spzx.manager.custom.interceptor;

import com.atguigu.spzx.common.exception.LoginException;
import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.common.result.ResultCodeEnum;
import com.atguigu.spzx.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //预检请求放行
        String method = request.getMethod();
        if (Objects.equals("OPTIONS", method)) {
            return true;
        }

        String token = request.getHeader("token");
        log.info("登录token为：" + token);
        if (token == null) {
            throw new LoginException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        Claims claims = JwtUtil.parseToken(token);
        //存储到thread local
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("清除thread local");
        LoginUserHolder.clean();
    }
}
