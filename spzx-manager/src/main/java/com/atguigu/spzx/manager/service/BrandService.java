package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.Brand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【brand(分类品牌)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface BrandService extends IService<Brand> {

    Page<Brand> findALlPage(Page<Brand> page);

    void saveBrand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrand(Integer id);

    List<Brand> findALlBrand();
}
