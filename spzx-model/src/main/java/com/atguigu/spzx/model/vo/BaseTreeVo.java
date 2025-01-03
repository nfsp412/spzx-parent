package com.atguigu.spzx.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public abstract class BaseTreeVo<T> implements Serializable {
    Long id;
    Long parentId;
    List<T> children;
}
