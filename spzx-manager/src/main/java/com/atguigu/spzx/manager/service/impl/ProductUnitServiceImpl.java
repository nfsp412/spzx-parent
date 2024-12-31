package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.ProductUnit;
import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.manager.mapper.ProductUnitMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product_unit(商品单位)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class ProductUnitServiceImpl extends ServiceImpl<ProductUnitMapper, ProductUnit>
        implements ProductUnitService {

    @Override
    public List<ProductUnit> findAllProductUnit() {
        return super.list();
    }
}




