package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginService {
    LoginVo login(LoginDto loginDto);


    ValidateCodeVo generateValidateCode();
}
