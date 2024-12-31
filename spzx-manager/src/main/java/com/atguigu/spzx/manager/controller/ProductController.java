package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.dto.ProductDto;
import com.atguigu.spzx.model.dto.SaveProductDto;
import com.atguigu.spzx.model.entity.Product;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.atguigu.spzx.model.vo.ProductVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/getProductPage/{page}/{limit}")
    public Result<Page<ProductVo>> getProductPage(@PathVariable("page") Integer page,
                                                  @PathVariable("limit") Integer limit,
                                                  ProductDto productDto) {
        System.out.println("===============" + productDto);
        Page<ProductVo> productPage = new Page<>(page, limit);
        Page<ProductVo> productPage1 = productService.getProductPage(productPage, productDto);
        return Result.ok(productPage1);
    }

    @PostMapping("/saveProduct")
    public Result saveProduct(@RequestBody SaveProductDto saveProductDto) {
        productService.saveProduct(saveProductDto);
        return Result.ok(null);
    }

    @GetMapping("/getProductById/{id}")
    public Result<ProductVo> getProductById(@PathVariable("id") Integer id) {

        ProductVo productVo = productService.getProductById(id);
        return Result.ok(productVo);
    }

    @PutMapping("/updateProductById")
    public Result updateProductById(@RequestBody SaveProductDto saveProductDto) {
        productService.updateProductById(saveProductDto);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public Result deleteProductById(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
        return Result.ok(null);
    }

    @GetMapping("/updateProductAuditStatus/{id}/{auditStatus}")
    public Result updateProductAuditStatus(@PathVariable("id") Long id,
                                           @PathVariable("auditStatus") Integer auditStatus) {
        productService.updateProductAuditStatus(id, auditStatus);
        return Result.ok(null);
    }

    @GetMapping("/updateProductStatus/{id}/{status}")
    public Result updateProductStatus(@PathVariable("id") Long id,
                                           @PathVariable("status") Integer status) {
        productService.updateProductStatus(id, status);
        return Result.ok(null);
    }
}
