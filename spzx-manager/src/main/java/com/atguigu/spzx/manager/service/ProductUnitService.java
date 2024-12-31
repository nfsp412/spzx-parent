package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.ProductUnit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product_unit(商品单位)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface ProductUnitService extends IService<ProductUnit> {

    List<ProductUnit> findAllProductUnit();
}
