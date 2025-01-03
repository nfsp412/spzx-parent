package com.atguigu.spzx.common.utils;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.anno.Log;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.model.entity.SysOperLog;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtil {
    /**
     * 操作执行之后调用
     *
     * @param sysLog
     * @param proceed
     * @param sysOperLog
     * @param status
     * @param errorMsg
     */
    public static void afterHandlerLog(Log sysLog,
                                       Object proceed,
                                       SysOperLog sysOperLog,
                                       int status) {
        if (sysLog.isSaveResponseData()) {
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        }
        sysOperLog.setStatus(status);

    }

    /**
     * 操作执行之前调用
     *
     * @param sysLog
     * @param joinPoint
     * @param sysOperLog
     */
    public static void beforeHandlerLog(Log sysLog,
                                        ProceedingJoinPoint joinPoint,
                                        SysOperLog sysOperLog) {

        // 设置操作模块名称
        sysOperLog.setTitle(sysLog.title());
        sysOperLog.setOperatorType(sysLog.operatorType().name());

        // 获取目标方法信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        sysOperLog.setMethod(method.getDeclaringClass().getName());

        // 获取请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        sysOperLog.setRequestMethod(request.getMethod());
        sysOperLog.setOperUrl(request.getRequestURI());
        sysOperLog.setOperIp(request.getRemoteAddr());

        // 设置请求参数
        if (sysLog.isSaveRequestData()) {
            String requestMethod = sysOperLog.getRequestMethod();
            if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                String params = Arrays.toString(joinPoint.getArgs());
                sysOperLog.setOperParam(params);
            }
        }
        sysOperLog.setOperName(LoginUserHolder.getLoginUser().getUsername());
    }

    public static void exceptionHandlerLog(Log sysLog, Object proceed, SysOperLog sysOperLog, int i, String message) {
        afterHandlerLog(sysLog,proceed,sysOperLog,i);
        sysOperLog.setErrorMsg(message);
    }
}
