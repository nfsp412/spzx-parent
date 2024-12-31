package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.common.utils.GeneralTreeUtil;
import com.atguigu.spzx.common.utils.MenuHelper;
import com.atguigu.spzx.common.utils.MenuTreeUtil;
import com.atguigu.spzx.model.entity.SysMenu1;
import com.atguigu.spzx.model.entity.SysUser;
import com.atguigu.spzx.model.vo.DynamicMenusVo;
import com.atguigu.spzx.model.vo.SysMenuVo;
import com.atguigu.spzx.model.vo.SysMenuVo1;
import com.atguigu.spzx.model.vo.SysMenuVo2;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author nfsp4
 * @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
 * @createDate 2024-12-24 22:35:04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

//    @Override
//    public List<SysMenuVo> findNodes() {
//
//        List<SysMenuVo> sysMenuVoList = sysMenuMapper.findNodes();
//        return MenuTreeUtil.buildTree(sysMenuVoList);
//    }

    /**
     * 测试
     *
     * @return
     */
    @Override
    public List<SysMenuVo1> findNodes() {

        List<SysMenuVo1> sysMenuVoList = sysMenuMapper.findNodes();
        return GeneralTreeUtil.buildTree(sysMenuVoList);
    }

    @Override
    public void saveSysMenu(SysMenu sysMenu) {

        super.save(sysMenu);
    }

    @Override
    public void updateSysMenuById(SysMenu sysMenu) {
        super.updateById(sysMenu);
    }

    @Override
    public void removeSysMenuById(Long id) {
        super.removeById(id);
    }

    @Override
    public Map<String, Object> findAllMenus(Long roleId) {
        HashMap<String, Object> map = new HashMap<>();

        //sysMenuList
        List<SysMenuVo1> sysMenuList = findNodes();

        //roleMenuIds
        List<Long> roleMenuIds = sysMenuMapper.findRoleMenuIds(roleId);

        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);
        return map;
    }

    @Override
    public List<DynamicMenusVo> findDynamicMenus() {
        LoginUser user = LoginUserHolder.getLoginUser();
        List<DynamicMenusVo> list = sysMenuMapper.findDynamicMenus(user.getUserId());
//        System.out.println(list);

        return GeneralTreeUtil.buildTree(list);
    }

    @Override
    public List<SysMenuVo2> findUserMenuList() {

        LoginUser user = LoginUserHolder.getLoginUser();
        Long userId = user.getUserId();         // 获取当前登录用户的id

        List<SysMenu1> sysMenuList = sysMenuMapper.selectListByUserId(userId);

        //构建树形数据
        List<SysMenu1> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo2> buildMenus(List<SysMenu1> menus) {

        List<SysMenuVo2> sysMenuVoList = new LinkedList<SysMenuVo2>();
        for (SysMenu1 sysMenu : menus) {
            SysMenuVo2 sysMenuVo = new SysMenuVo2();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu1> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
