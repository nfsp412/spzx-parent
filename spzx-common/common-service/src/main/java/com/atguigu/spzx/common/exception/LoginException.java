package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.common.result.ResultCodeEnum;
import lombok.Getter;


@Getter
public class LoginException extends RuntimeException {
    private Integer code;

    public LoginException() {
    }


    public LoginException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public LoginException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "LoginException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
