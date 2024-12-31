package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.vo.ValidateCodeVo;

public interface LoginService {
    ValidateCodeVo generateValidateCode();
}
