package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.ProductDto;
import com.atguigu.spzx.model.dto.SaveProductDto;
import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.model.vo.ProductVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【product(商品)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface ProductService extends IService<Product> {

    Page<ProductVo> getProductPage(Page<ProductVo> productPage, ProductDto productDto);

    void saveProduct(SaveProductDto saveProductDto);

    ProductVo getProductById(Integer id);

    void updateProductById(SaveProductDto saveProductDto);

    void deleteProductById(Integer id);

    void updateProductAuditStatus(Long id, Integer auditStatus);

    void updateProductStatus(Long id, Integer status);
}
