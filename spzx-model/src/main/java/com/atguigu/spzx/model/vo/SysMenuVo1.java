package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo1 extends BaseVo<SysMenuVo1> {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;
}

