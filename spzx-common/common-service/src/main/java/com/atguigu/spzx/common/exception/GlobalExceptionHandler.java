package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result error(LoginException e) {
        log.error("登录异常：{}", e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

}
