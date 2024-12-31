package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.model.entity.CategoryBrand;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category_brand(分类品牌)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface CategoryBrandService extends IService<CategoryBrand> {

    Page<CategoryBrandVo> findAllPage(Page<CategoryBrandVo> page, CategoryBrandDto categoryBrandDto);

    void saveCategoryBrandData(CategoryBrand categoryBrandDto);

    void updateCategoryBrandData(CategoryBrand categoryBrandDto);

    void deleteCategoryBrandData(Integer id);

    List<Brand> findBrandByCategoryId(Integer category3Id);
}
