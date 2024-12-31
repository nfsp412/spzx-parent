package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.UserAddress;
import com.atguigu.spzx.manager.service.UserAddressService;
import com.atguigu.spzx.manager.mapper.UserAddressMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【user_address(用户地址表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>
        implements UserAddressService {

}




