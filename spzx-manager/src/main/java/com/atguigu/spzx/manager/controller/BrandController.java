package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.Brand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @GetMapping("/findALlPage/{current}/{size}")
    public Result<Page<Brand>> findALlPage(@PathVariable("current") Integer current,
                                           @PathVariable("size") Integer size) {
        Page<Brand> page = new Page<>(current, size);
        Page<Brand> list = brandService.findALlPage(page);
        return Result.ok(list);
    }

    @PostMapping("/saveBrand")
    public Result saveBrand(@RequestBody Brand brand) {
        brandService.saveBrand(brand);
        return Result.ok(null);
    }

    @PutMapping("/updateBrand")
    public Result updateBrand(@RequestBody Brand brand) {
        brandService.updateBrand(brand);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteBrand/{id}")
    public Result deleteBrand(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
        return Result.ok(null);
    }

    @GetMapping("/findALlBrand")
    public Result<List<Brand>> findALlBrand() {
        List<Brand> list = brandService.findALlBrand();
        return Result.ok(list);
    }
}
