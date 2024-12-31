package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【category(商品分类)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.Category
 */
public interface CategoryMapper extends BaseMapper<Category> {

    void batchInsert(List<Category> cachedDataList);
}




