package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.SysMenuService;

import com.atguigu.spzx.model.entity.SysMenu;
import com.atguigu.spzx.model.vo.SysMenuVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/findNodes")
    public Result<List<SysMenuVo>> findNodes() {
        List<SysMenuVo> sysMenuVoList = sysMenuService.findNodes();
        return Result.ok(sysMenuVoList);
    }

    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveSysMenu(sysMenu);
        return Result.ok(null);
    }

    @PutMapping("/updateSysMenuById")
    public Result updateSysMenuById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenuById(sysMenu);
        return Result.ok(null);
    }

    @DeleteMapping("/removeSysMenuById/{id}")
    public Result removeSysMenuById(@PathVariable("id") Long id) {
        sysMenuService.removeSysMenuById(id);
        return Result.ok(null);
    }

    @GetMapping("/findAllMenus/{roleId}")
    public Result<Map<String,Object>> findAllMenus(@PathVariable("roleId") Long roleId) {
        Map<String,Object> map = sysMenuService.findAllMenus(roleId);
        return Result.ok(map);
    }
}
