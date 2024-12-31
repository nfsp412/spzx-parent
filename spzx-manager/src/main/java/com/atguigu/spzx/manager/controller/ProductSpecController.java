package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.entity.CategoryBrand;
import com.atguigu.spzx.model.entity.ProductSpec;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productSpec")
public class ProductSpecController {
    @Resource
    private ProductSpecService productSpecService;

    @GetMapping("/selectAllProductSpec/{current}/{size}")
    public Result<Page<ProductSpec>> selectAllProductSpec(@PathVariable("current") Integer current,
                                                          @PathVariable("size") Integer size) {
        Page<ProductSpec> page = new Page<>(current, size);
        Page<ProductSpec> productSpecPage = productSpecService.selectAllProductSpec(page);
        return Result.ok(productSpecPage);
    }

    @PostMapping("/saveProductSpec")
    public Result saveProductSpec(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.ok(null);
    }

    @PutMapping("/updateProductSpec")
    public Result UpdateProductSpec(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.ok(null);
    }

    @DeleteMapping("/deleteProductSpec/{id}")
    public Result deleteProductSpec(@PathVariable("id") Integer id) {
        productSpecService.removeById(id);
        return Result.ok(null);
    }

    @GetMapping("/findAllProductSpec")
    public Result<List<ProductSpec>> findAllProductSpec() {
        List<ProductSpec> productSpecList = productSpecService.findAllProductSpec();
        return Result.ok(productSpecList);
    }

    @GetMapping("/findProductSkuByBrandId/{id}")
    public Result<List<ProductSpec>> findProductSkuByBrandId(@PathVariable("id") Integer id) {
        List<ProductSpec> productSpecList = productSpecService.findProductSkuByBrandId(id);
        return Result.ok(productSpecList);
    }
}
