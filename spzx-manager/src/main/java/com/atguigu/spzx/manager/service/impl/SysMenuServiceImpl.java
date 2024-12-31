package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.login.LoginUser;
import com.atguigu.spzx.common.login.LoginUserHolder;
import com.atguigu.spzx.common.utils.GeneralTreeUtil;
import com.atguigu.spzx.model.vo.SysMenuVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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



    /**
     * 测试
     *
     * @return
     */
    @Override
    public List<SysMenuVo> findNodes() {

        List<SysMenuVo> sysMenuVoList = sysMenuMapper.findNodes();
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
        List<SysMenuVo> sysMenuList = findNodes();

        //roleMenuIds
        List<Long> roleMenuIds = sysMenuMapper.findRoleMenuIds(roleId);

        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);
        return map;
    }

    @Override
    public List<SysMenuVo> menus() {
        LoginUser user = LoginUserHolder.getLoginUser();
        List<SysMenuVo> list = sysMenuMapper.menus(user.getUserId());

        return GeneralTreeUtil.buildTree(list);
    }

}

