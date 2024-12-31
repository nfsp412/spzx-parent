package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.OrderItem;
import com.atguigu.spzx.manager.service.OrderItemService;
import com.atguigu.spzx.manager.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【order_item(订单项信息)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
        implements OrderItemService {

}




