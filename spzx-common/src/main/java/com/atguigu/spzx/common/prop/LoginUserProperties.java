package com.atguigu.spzx.common.prop;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spzx.auth")
@Data
//@ConditionalOnProperty(prefix = "spzx.auth")
public class LoginUserProperties {

    private List<String> noAuthUrls;
}
