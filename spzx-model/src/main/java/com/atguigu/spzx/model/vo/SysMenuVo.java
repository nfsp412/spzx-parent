package com.atguigu.spzx.model.vo;

import com.atguigu.spzx.model.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo extends SysMenu {
    private List<SysMenu> children;

    @Override
    public String toString() {
        String s = super.toString();
        StringBuilder sb = new StringBuilder(s);
        sb.append(" [");
        sb.append("children=").append(children);
        sb.append("]");
        return sb.toString();
    }
}

