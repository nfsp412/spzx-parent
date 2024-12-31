package com.atguigu.spzx.common.utils;


import com.atguigu.spzx.model.entity.SysMenu;

import com.atguigu.spzx.model.vo.SysMenuVo;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeUtil {
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

        List<SysMenuVo> treeList = MenuTreeUtil.buildTree(sysMenuVoList); //构建树形数据
        System.out.println(treeList);


    }

    /**
     * 使用递归方法建菜单
     *
     * @param sysMenuVoList 所有的菜单明细数据的集合,SysMenuVo泛型
     * @return 构建完毕的带有children属性的菜单明细数据的集合, children属性是当前菜单对应的子菜单的明细,,SysMenuVo泛型
     */
    public static List<SysMenuVo> buildTree(List<SysMenuVo> sysMenuVoList) {
        List<SysMenuVo> trees = new ArrayList<>();
        for (SysMenuVo sysMenuVo : sysMenuVoList) {
            if (sysMenuVo.getParentId() == 0) {
                trees.add(findChildren(sysMenuVo, sysMenuVoList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param sysMenuVoList 全部的菜单数据的集合
     * @param sysMenuVo     parent id为0的菜单数据
     * @return 返回的是创建了children子菜单的SysMenu
     */
    private static SysMenuVo findChildren(SysMenuVo sysMenuVo, List<SysMenuVo> sysMenuVoList) {
        sysMenuVo.setChildren(new ArrayList<>());
        //遍历所有的菜单明细数据
        for (SysMenuVo it : sysMenuVoList) {
            //如果传入的parent id为0的菜单明细数据的id
            //和当前遍历的菜单明细数据的parent id相同
            //证明当前遍历的这个菜单明细数据,是传入的数据的子节点
            if (sysMenuVo.getId().longValue() == it.getParentId().longValue()) {
                //if (sysMenu.getChildren() == null) {
                //    sysMenu.setChildren(new ArrayList<>());
                //}
                //找到子节点后,将子节点的数据添加到children属性中
                /*
                但是需要注意,我们不知道子节点是否还有子节点,所以还应该以当前这个子节点作为父节点,
                继续去寻找他的子节点,
                如果找不到,那children属性保持空集合即可,
                 */
                sysMenuVo.getChildren().add(findChildren(it, sysMenuVoList));

            }
        }
        //递归要有出口,也就是无论是否找到子节点,我们都应该将设置了children属性的菜单明细数据返回
        return sysMenuVo;
    }
}
