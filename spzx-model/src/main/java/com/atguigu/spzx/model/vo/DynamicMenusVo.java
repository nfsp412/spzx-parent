package com.atguigu.spzx.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class DynamicMenusVo extends BaseVo<DynamicMenusVo> {
    private String title;
    private String name;
    private List<DynamicMenusVo> children;
}

