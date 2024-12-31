package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.Category;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends BaseTreeVo<CategoryVo> {
    /**
     * 分类名称
     */
    private String name;

    /**
     * 图片url
     */
    private String imageUrl;

//    /**
//     * 父分类id
//     */
//    private Long parentId;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNum;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Boolean hasChildren;
//    private List<Category> children;


    @Override
    public String toString() {
        return "CategoryVo{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                ", orderNum=" + orderNum +
                ", hasChildren=" + hasChildren +
                ", id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
