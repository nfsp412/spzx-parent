package com.atguigu.spzx.common.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spzx.auth")
@Data
public class LoginUserProperties {

    private List<String> noAuthUrls;
}
