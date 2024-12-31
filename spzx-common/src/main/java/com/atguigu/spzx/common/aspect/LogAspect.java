package com.atguigu.spzx.common.aspect;

import com.atguigu.spzx.common.anno.Log;
import com.atguigu.spzx.common.utils.LogUtil;
import com.atguigu.spzx.model.entity.SysOperLog;
import com.atguigu.spzx.common.service.AsyncOperatorLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Resource
    private AsyncOperatorLogService asyncOperatorLogService;

    /**
     * 只要方法上添加自定义注解,就会执行该环绕通知方法
     *
     * @param joinPoint
     * @param sysLog
     * @return
     */
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
//        String title = sysLog.title();
//        log.info("LogAspect...doAroundAdvice方法执行了" + title);
        //创建数据库实体类对象
        SysOperLog sysOperLog = new SysOperLog();

        //前置方法
        LogUtil.beforeHandlerLog(sysLog, joinPoint, sysOperLog);

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();              // 执行业务方法

            //后置方法
            LogUtil.afterHandlerLog(sysLog, proceed, sysOperLog, 0);
        } catch (Throwable e) {                         // 代码执行进入到catch中，业务方法执行产生异常
            //异常方法
            LogUtil.exceptionHandlerLog(sysLog, proceed, sysOperLog, 1, e.getMessage());

            /*
            因为默认事务切面的优先级要高于自定义切面,所以在这里如果不继续抛出异常,一旦添加了事务的方法出现了异常
            而在这里进行try catch处理了异常,那么事务那里就无法感知到异常了,因此就无法进行回滚了
            所以在这里应该把出现的异常继续向上抛出

             */
            throw new RuntimeException(e);

        }finally {
            //数据写入数据库,如果只是想保存日志或者打印控制台,则不需要这一步了
            asyncOperatorLogService.saveSysOperLog(sysOperLog);
        }

        return proceed;                                // 返回执行结果
    }
}
