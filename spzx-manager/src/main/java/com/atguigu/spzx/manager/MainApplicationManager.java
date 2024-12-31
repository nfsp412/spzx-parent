
package com.atguigu.spzx.manager;


import com.atguigu.spzx.common.anno.EnableLogAspect;
import com.atguigu.spzx.common.prop.LoginUserProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.atguigu.spzx.manager.mapper")
@ComponentScan("com.atguigu.spzx")
@EnableConfigurationProperties(value = {LoginUserProperties.class})
//@EnableLogAspect
@EnableAsync
public class MainApplicationManager {
    public static void main(String[] args) {
        SpringApplication.run(MainApplicationManager.class, args);
    }
}
