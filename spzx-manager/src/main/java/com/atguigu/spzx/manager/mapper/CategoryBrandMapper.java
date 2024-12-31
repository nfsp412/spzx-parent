package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.model.entity.CategoryBrand;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category_brand(分类品牌)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.CategoryBrand
 */
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {

    Page<CategoryBrandVo> findAllPage(Page<CategoryBrandVo> page, CategoryBrandDto categoryBrandDto);

    List<Brand> findBrandByCategoryId(Integer category3Id);
}




