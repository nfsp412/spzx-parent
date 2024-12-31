package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.BaseEntity;
import lombok.Data;

@Data
public class CategoryBrandVo extends BaseEntity {

    private Long brandId;
    private Long categoryId;

    // 扩展的属性用来封装前端所需要的数据
    private String categoryName;
    private String brandName;
    private String logo;

}
