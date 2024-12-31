package com.atguigu.spzx.product.service.impl;







import com.atguigu.spzx.model.entity.Product;

import com.atguigu.spzx.model.entity.ProductSku;

import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.service.ProductService;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductSku> findProductSkuList() {
        return productMapper.findProductSkuList();
    }
}
