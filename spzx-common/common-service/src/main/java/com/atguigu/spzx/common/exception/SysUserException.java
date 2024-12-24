package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.common.result.ResultCodeEnum;
import lombok.Getter;


@Getter
public class SysUserException extends RuntimeException {
    private Integer code;

    public SysUserException() {
    }


    public SysUserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public SysUserException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "SysUserException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
