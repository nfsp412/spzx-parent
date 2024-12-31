package com.atguigu.spzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    public Long id;

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
}