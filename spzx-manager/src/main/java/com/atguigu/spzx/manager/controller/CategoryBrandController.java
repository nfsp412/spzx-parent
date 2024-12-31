package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.model.entity.CategoryBrand;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {
    @Resource
    private CategoryBrandService categoryBrandService;

    @GetMapping("/findAllPage/{current}/{size}")
    public Result<Page<CategoryBrandVo>> findAllPage(@PathVariable("current") Integer current,
                                                     @PathVariable("size") Integer size,
                                                     CategoryBrandDto categoryBrandDto) {
        Page<CategoryBrandVo> page = new Page<>(current, size);
        Page<CategoryBrandVo> categoryBrandVoPage = categoryBrandService.findAllPage(page, categoryBrandDto);
        return Result.ok(categoryBrandVoPage);
    }

    @PostMapping("/saveCategoryBrandData")
    public Result saveCategoryBrandData(@RequestBody CategoryBrand categoryBrandDto) {
        categoryBrandService.saveCategoryBrandData(categoryBrandDto);
        return Result.ok(null);
    }

    @PutMapping("/updateCategoryBrandData")
    public Result updateCategoryBrandData(@RequestBody CategoryBrand categoryBrandDto) {
        categoryBrandService.updateCategoryBrandData(categoryBrandDto);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteCategoryBrandData/{id}")
    public Result deleteCategoryBrandData(@PathVariable("id") Integer id) {
        categoryBrandService.deleteCategoryBrandData(id);
        return Result.ok(null);
    }

    @GetMapping("/findBrandByCategoryId/{category3Id}")
    public Result<List<Brand>> findBrandByCategoryId(@PathVariable("category3Id") Integer category3Id) {
        List<Brand> brandList = categoryBrandService.findBrandByCategoryId(category3Id);
        return Result.ok(brandList);
    }

}
