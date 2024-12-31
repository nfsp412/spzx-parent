package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.model.vo.DynamicMenusVo;
import com.atguigu.spzx.model.vo.SysMenuVo;
import com.atguigu.spzx.model.vo.SysMenuVo1;
import com.atguigu.spzx.model.vo.SysMenuVo2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author nfsp4
 * @description 针对表【sys_menu(菜单表)】的数据库操作Service
 * @createDate 2024-12-24 22:35:04
 */
public interface SysMenuService extends IService<SysMenu> {

//    List<SysMenuVo> findNodes();

    List<SysMenuVo1> findNodes();

    void saveSysMenu(SysMenu sysMenu);

    void updateSysMenuById(SysMenu sysMenu);

    void removeSysMenuById(Long id);

    Map<String, Object> findAllMenus(Long roleId);

    List<DynamicMenusVo> findDynamicMenus();

    List<SysMenuVo2> findUserMenuList();
}
