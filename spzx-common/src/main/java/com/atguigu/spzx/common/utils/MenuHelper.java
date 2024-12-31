package com.atguigu.spzx.common.utils;

import com.atguigu.spzx.model.entity.SysMenu1;

import java.util.ArrayList;
import java.util.List;

// com.atguigu.spzx.manager.helper
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu1> buildTree(List<SysMenu1> sysMenuList) {
        List<SysMenu1> trees = new ArrayList<>();
        for (SysMenu1 SysMenu1 : sysMenuList) {
            if (SysMenu1.getParentId().longValue() == 0) {
                trees.add(findChildren(SysMenu1,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenu1 findChildren(SysMenu1 SysMenu1, List<SysMenu1> treeNodes) {
        SysMenu1.setChildren(new ArrayList<SysMenu1>());
        for (SysMenu1 it : treeNodes) {
            if(SysMenu1.getId().longValue() == it.getParentId().longValue()) {
                //if (SysMenu1.getChildren() == null) {
                //    SysMenu1.setChildren(new ArrayList<>());
                //}
                SysMenu1.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return SysMenu1;
    }
}
