package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.model.entity.ProductSpec;
import com.atguigu.spzx.model.entity.ProductUnit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {
    @Resource
    private ProductUnitService productUnitService;

    @GetMapping("/findAllProductUnit")
    public Result<List<ProductUnit>> findAllProductUnit() {
        List<ProductUnit> productUnitList = productUnitService.findAllProductUnit();
        return Result.ok(productUnitList);
    }
}
