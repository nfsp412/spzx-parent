package com.atguigu.spzx.common.utils;


import com.atguigu.spzx.model.vo.BaseTreeVo;
import com.atguigu.spzx.model.vo.SysMenuVo;

import java.util.ArrayList;
import java.util.List;

public class GeneralTreeUtil {
    public static void main(String[] args) {
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();

        SysMenuVo m1 = new SysMenuVo();
        m1.setId(1L);
        m1.setParentId(0L);
        m1.setTitle("系统管理");

        SysMenuVo m2 = new SysMenuVo();
        m2.setId(2L);
        m2.setParentId(1L);
        m2.setTitle("用户管理");

        SysMenuVo m3 = new SysMenuVo();
        m3.setId(3L);
        m3.setParentId(1L);
        m3.setTitle("角色管理");

        sysMenuVoList.add(m1);
        sysMenuVoList.add(m2);
        sysMenuVoList.add(m3);

        List<SysMenuVo> treeList = GeneralTreeUtil.buildTree(sysMenuVoList); //构建树形数据
        System.out.println(treeList);

    }

    /**
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends BaseTreeVo<T>> List<T> buildTree(List<T> list) {

        List<T> trees = new ArrayList<>();
        for (T t : list) {
            if (t.getParentId() == 0) {
                trees.add(findChildren(t, list));
            } else {
                t.setChildren(null);
            }
        }
        return trees;
    }

    /**
     * @param t
     * @param list
     * @param <T>
     * @return
     */
    private static <T extends BaseTreeVo<T>> T findChildren(T t, List<T> list) {
        t.setChildren(new ArrayList<>());

        for (T it : list) {

            if (t.getId().longValue() == it.getParentId().longValue()) {

                t.getChildren().add(findChildren(it, list));

            }

        }

        return t;
    }
}
