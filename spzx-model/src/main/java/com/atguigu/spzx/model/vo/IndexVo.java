package com.atguigu.spzx.model.vo;


import com.atguigu.spzx.model.entity.Category;
import com.atguigu.spzx.model.entity.ProductSku;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {

    private List<Category> categoryList;       // 一级分类的类别数据
    private List<ProductSku> productSkuList;   // 畅销商品列表数据

}