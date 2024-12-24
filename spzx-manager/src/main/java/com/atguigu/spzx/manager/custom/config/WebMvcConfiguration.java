package com.atguigu.spzx.manager.custom.config;

import com.atguigu.spzx.manager.custom.interceptor.AuthenticationInterceptor;
import com.atguigu.spzx.manager.custom.prop.LoginUserProperties;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Resource
    private LoginUserProperties loginUserProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(loginUserProperties.getNoAuthUrls());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");                // 允许所有的请求头
    }
}
