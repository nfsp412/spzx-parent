package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.AsyncOperatorLogMapper;
import com.atguigu.spzx.manager.mapper.BrandMapper;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.model.entity.SysOperLog;
import com.atguigu.spzx.service.AsyncOperatorLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperatorLogServiceImpl extends ServiceImpl<AsyncOperatorLogMapper, SysOperLog>
        implements AsyncOperatorLogService {
    @Resource
    private AsyncOperatorLogMapper asyncOperatorLogMapper;

    /**
     * 异步方法
     *
     * @param sysOperLog
     */
    @Async
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        asyncOperatorLogMapper.insert(sysOperLog);
    }
}
