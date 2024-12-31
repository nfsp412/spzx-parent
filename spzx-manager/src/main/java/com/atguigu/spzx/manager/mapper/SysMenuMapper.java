package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.model.entity.SysMenu1;
import com.atguigu.spzx.model.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author nfsp4
 * @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
 * @createDate 2024-12-24 22:35:04
 * @Entity com.atguigu.spzx.model.entity.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    List<SysMenuVo> findNodes();

    List<Long> findRoleMenuIds(Long roleId);



    List<SysMenu> selectListByUserId(Long userId);

    List<SysMenuVo> menus(Long userId);
}




