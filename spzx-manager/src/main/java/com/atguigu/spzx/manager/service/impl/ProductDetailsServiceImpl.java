package com.atguigu.spzx.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.ProductDetails;
import com.atguigu.spzx.manager.service.ProductDetailsService;
import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import org.springframework.stereotype.Service;

/**
 * @author nfsp4
 * @description 针对表【product_details(商品sku属性表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class ProductDetailsServiceImpl extends ServiceImpl<ProductDetailsMapper, ProductDetails>
        implements ProductDetailsService {

}




