package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.OrderLog;
import com.atguigu.spzx.manager.service.OrderLogService;
import com.atguigu.spzx.manager.mapper.OrderLogMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【order_log(订单操作日志记录)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class OrderLogServiceImpl extends ServiceImpl<OrderLogMapper, OrderLog>
        implements OrderLogService {

}




