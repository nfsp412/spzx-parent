package com.atguigu.spzx.product.service;



import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.model.entity.ProductSku;


import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface ProductService extends IService<Product> {


    List<ProductSku> findProductSkuList();
}
