package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.model.vo.CategoryVo;
import com.atguigu.spzx.product.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/product/category")
@CrossOrigin
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/findCategoryTree")
    public Result<List<CategoryVo>> findCategoryTree() {
        List<CategoryVo> categoryVoList = categoryService.findCategoryTree();
        return Result.ok(categoryVoList);
    }
}
