package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.OrderInfo;
import com.atguigu.spzx.manager.service.OrderInfoService;
import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【order_info(订单)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
        implements OrderInfoService {

}




