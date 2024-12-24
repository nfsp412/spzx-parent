package com.atguigu.spzx.common.result;

import lombok.Data;

@Data
//@Schema(description = "响应结果实体类")
public class Result<T> {

    //返回码
//    @Schema(description = "业务状态码")
    private Integer code;

    //返回消息
//    @Schema(description = "响应消息")
    private String message;

    //返回数据
//    @Schema(description = "业务数据")
    private T data;

    // 私有化构造
    private Result() {
    }

    // 返回数据
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 通过枚举构造Result对象
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        return build(body, resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T body) {
        return build(body, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> build = build(null);
        build.setCode(code);
        build.setMessage(message);
        return build;
    }

}
