package com.atguigu.spzx.product.mapper;


import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.model.entity.ProductSku;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.Product
 */
public interface ProductMapper extends BaseMapper<Product> {


    List<ProductSku> findProductSkuList();
}
