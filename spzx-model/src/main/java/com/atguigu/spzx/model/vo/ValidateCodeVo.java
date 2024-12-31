package com.atguigu.spzx.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "验证码响应结果实体类")
public class ValidateCodeVo {

    @Schema(description = "验证码key")
    private String key;        // 验证码的redis的存储的key,value是验证码的值

    @Schema(description = "验证码value")
    private String image;      // 图片(对应的base64字符串)

}