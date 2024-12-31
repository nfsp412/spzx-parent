package com.atguigu.spzx.common.anno;

import com.atguigu.spzx.common.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的第一个作用,定义一些要存储的属性变量,然后在切面类中添加该注解
 * 只要标注了该注解的地方,就会执行切面类中对应的方法,例如环绕方法
 */
@Target({ElementType.METHOD}) //该注解应用在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface Log {
    String title(); //模块名称
    OperatorType operatorType() default OperatorType.MANAGE; //操作人员类型
    int businessType(); //业务类型 0其他,1新增,2修改,3删除
    boolean isSaveRequestData() default true; //是否保存请求参数
    boolean isSaveResponseData() default true; //是否保存响应参数
}
