package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.model.entity.ProductSku;
import com.atguigu.spzx.model.vo.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/product/index")
@CrossOrigin
@RestController
public class IndexController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @GetMapping
    public Result<IndexVo> index() {
        List<Category> categoryList = categoryService.findCategoryList();
        List<ProductSku> productSkuList = productService.findProductSkuList();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.ok(indexVo);
    }

}
