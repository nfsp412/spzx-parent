package com.atguigu.spzx.product.service.impl;







import com.atguigu.spzx.common.utils.GeneralTreeUtil;
import com.atguigu.spzx.model.entity.Category;

import com.atguigu.spzx.model.vo.CategoryVo;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category(商品分类)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {


    @Override
    public List<Category> findCategoryList() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId,0);
        return super.list(wrapper);
    }

    @Override
    public List<CategoryVo> findCategoryTree() {
        List<Category> categoryList = super.list();
        ArrayList<CategoryVo> categoryVoList = new ArrayList<>();
        BeanUtils.copyProperties(categoryList, categoryVoList);
        System.out.println(categoryVoList);
        return null;
    }
}
