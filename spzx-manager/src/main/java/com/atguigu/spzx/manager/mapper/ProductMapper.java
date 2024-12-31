package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.ProductDto;
import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.model.vo.ProductVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.Product
 */
public interface ProductMapper extends BaseMapper<Product> {

    Page<ProductVo> getProductPage(Page<ProductVo> productPage, ProductDto productDto);

    ProductVo getProductById(Integer id);
}




