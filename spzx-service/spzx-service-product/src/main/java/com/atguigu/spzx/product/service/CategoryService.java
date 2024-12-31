package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.Category;

import com.atguigu.spzx.model.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;



import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category(商品分类)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface CategoryService extends IService<Category> {


    List<Category> findCategoryList();

    List<CategoryVo> findCategoryTree();
}
