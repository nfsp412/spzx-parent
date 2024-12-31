package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.OrderStatistics;
import com.atguigu.spzx.manager.service.OrderStatisticsService;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【order_statistics(订单统计)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class OrderStatisticsServiceImpl extends ServiceImpl<OrderStatisticsMapper, OrderStatistics>
        implements OrderStatisticsService {

}




