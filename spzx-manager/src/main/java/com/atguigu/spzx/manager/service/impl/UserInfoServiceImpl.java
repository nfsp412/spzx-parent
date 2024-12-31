package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.UserInfo;
import com.atguigu.spzx.manager.service.UserInfoService;
import com.atguigu.spzx.manager.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【user_info(会员表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

}




