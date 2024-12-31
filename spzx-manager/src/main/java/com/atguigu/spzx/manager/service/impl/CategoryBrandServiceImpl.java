package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.model.dto.CategoryBrandDto;
import com.atguigu.spzx.model.entity.Brand;
import com.atguigu.spzx.model.vo.CategoryBrandVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.CategoryBrand;
import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.manager.mapper.CategoryBrandMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category_brand(分类品牌)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand>
        implements CategoryBrandService {
    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Page<CategoryBrandVo> findAllPage(Page<CategoryBrandVo> page, CategoryBrandDto categoryBrandDto) {
        return categoryBrandMapper.findAllPage(page,categoryBrandDto);
    }

    @Override
    public void saveCategoryBrandData(CategoryBrand categoryBrandDto) {
        LambdaQueryWrapper<CategoryBrand> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryBrand::getCategoryId,categoryBrandDto.getCategoryId());
        wrapper.eq(CategoryBrand::getBrandId, categoryBrandDto.getBrandId());
        Long count = categoryBrandMapper.selectCount(wrapper);
        if (count != 0) {
            throw new RuntimeException("重复添加失败");
        }
        super.save(categoryBrandDto);
    }

    @Override
    public void updateCategoryBrandData(CategoryBrand categoryBrandDto) {
        super.updateById(categoryBrandDto);
    }

    @Override
    public void deleteCategoryBrandData(Integer id) {
        super.removeById(id);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Integer category3Id) {
        return categoryBrandMapper.findBrandByCategoryId(category3Id);
    }
}




