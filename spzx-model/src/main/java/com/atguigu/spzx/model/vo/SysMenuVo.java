package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.SysMenu;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysMenuVo extends BaseTreeVo<SysMenuVo>{
    /**
     * 菜单标题
     */
    private String title;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

//    private List<SysMenu> children;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    public Date updateTime;

    /**
     * 删除标记（0:可用 1:不可用）
     */
    @JsonIgnore
    public Integer isDeleted;


    @Override
    public String toString() {
        return "SysMenuVo{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", sortValue=" + sortValue +
                ", status=" + status +
                ", id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}

