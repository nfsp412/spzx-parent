package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.ProductSpec;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product_spec(商品规格)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface ProductSpecService extends IService<ProductSpec> {

    Page<ProductSpec> selectAllProductSpec(Page<ProductSpec> page);

    List<ProductSpec> findAllProductSpec();

    List<ProductSpec> findProductSkuByBrandId(Integer id);
}
