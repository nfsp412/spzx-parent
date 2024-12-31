package com.atguigu.spzx.model.vo;

import lombok.Data;

@Data
public class BaseVoImpl extends BaseVo<BaseVoImpl>{
    @Override
    public String toString() {
        return "BaseVoImpl{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
