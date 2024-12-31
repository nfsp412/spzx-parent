package com.atguigu.spzx.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo2 {
    private String title;
    private String name;
    private List<SysMenuVo2> children;
}

