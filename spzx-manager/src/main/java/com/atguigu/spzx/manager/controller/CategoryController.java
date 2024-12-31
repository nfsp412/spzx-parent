package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.CategoryService;

import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.model.vo.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleState;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/findByParentId/{id}")
    public Result findByParentId(@PathVariable("id") Long id) {
        List<CategoryVo> list = categoryService.findByParentId(id);
        return Result.ok(list);
    }

    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {

        categoryService.exportData(response);
    }

    @GetMapping("/exportTemplate")
    public void exportTemplate(HttpServletResponse response) {

        categoryService.exportTemplate(response);
    }

    @PostMapping("/importData")
    public Result importData(@RequestParam MultipartFile file) {
        categoryService.importData(file);
        return Result.ok(null);
    }
}
