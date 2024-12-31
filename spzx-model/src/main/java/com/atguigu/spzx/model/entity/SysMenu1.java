package com.atguigu.spzx.model.entity;


import lombok.Data;

import java.util.List;

// com.atguigu.spzx.model.entity.system
@Data
public class SysMenu1 extends BaseEntity {

    private Long parentId;
    private String title;
    private String component;
    private Integer sortValue;
    private Integer status;

    // 下级列表
    private List<SysMenu1> children;

}
