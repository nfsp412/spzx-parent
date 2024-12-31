package com.atguigu.spzx.model.vo;

import lombok.Data;

@Data
public class BaseVoImpl2 extends BaseVo<BaseVoImpl2>{
    private String name;

    @Override
    public String toString() {
        return "BaseVoImpl2{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
