package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.PaymentInfo;
import com.atguigu.spzx.manager.service.PaymentInfoService;
import com.atguigu.spzx.manager.mapper.PaymentInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【payment_info(付款信息表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
        implements PaymentInfoService {

}




