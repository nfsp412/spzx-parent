package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends Category {
    private Boolean hasChildren;
    private List<Category> children;
}
