package com.atguigu.spzx.common.utils;


import com.atguigu.spzx.model.vo.BaseVo;
import com.atguigu.spzx.model.vo.BaseVoImpl;
import com.atguigu.spzx.model.vo.BaseVoImpl2;
import com.atguigu.spzx.model.vo.SysMenuVo;

import java.util.ArrayList;
import java.util.List;

public class GeneralTreeUtil {
    public static void main(String[] args) {
//        List<BaseVoImpl> list = new ArrayList<>();
//
//        BaseVoImpl m1 = new BaseVoImpl();
//        m1.setId(1L);
//        m1.setParentId(0L);
//
//
//        BaseVoImpl m2 = new BaseVoImpl();
//        m2.setId(2L);
//        m2.setParentId(1L);
//
//
//        BaseVoImpl m3 = new BaseVoImpl();
//        m3.setId(3L);
//        m3.setParentId(1L);
//
//
//        list.add(m1);
//        list.add(m2);
//        list.add(m3);
//
//        List<BaseVoImpl> treeList = GeneralTreeUtil.buildTree(list); //构建树形数据
//        System.out.println(treeList);

        List<BaseVoImpl2> list = new ArrayList<>();

        BaseVoImpl2 m1 = new BaseVoImpl2();
        m1.setId(1L);
        m1.setParentId(0L);
        m1.setName("father");

        BaseVoImpl2 m2 = new BaseVoImpl2();
        m2.setId(2L);
        m2.setParentId(1L);
        m2.setName("child1");

        BaseVoImpl2 m3 = new BaseVoImpl2();
        m3.setId(3L);
        m3.setParentId(1L);
        m3.setName("child2");

        list.add(m1);
        list.add(m2);
        list.add(m3);

        List<BaseVoImpl2> treeList = GeneralTreeUtil.buildTree(list); //构建树形数据
        System.out.println(treeList);

    }

    /**
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends BaseVo<T>> List<T> buildTree(List<T> list) {
        List<T> trees = new ArrayList<>();
        for (T t : list) {
            if (t.getParentId() == 0) {
                trees.add(findChildren(t, list));
            }else{
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
    private static <T extends BaseVo<T>> T findChildren(T t, List<T> list) {
        t.setChildren(new ArrayList<>());
        //遍历所有的菜单明细数据
        for (T it : list) {
            //如果传入的parent id为0的菜单明细数据的id
            //和当前遍历的菜单明细数据的parent id相同
            //证明当前遍历的这个菜单明细数据,是传入的数据的子节点
            if (t.getId().longValue() == it.getParentId().longValue()) {
                //if (sysMenu.getChildren() == null) {
                //    sysMenu.setChildren(new ArrayList<>());
                //}
                //找到子节点后,将子节点的数据添加到children属性中
                /*
                但是需要注意,我们不知道子节点是否还有子节点,所以还应该以当前这个子节点作为父节点,
                继续去寻找他的子节点,
                如果找不到,那children属性保持空集合即可,
                 */
                t.getChildren().add(findChildren(it, list));

            }

        }
        //递归要有出口,也就是无论是否找到子节点,我们都应该将设置了children属性的菜单明细数据返回

        return t;
    }
}
