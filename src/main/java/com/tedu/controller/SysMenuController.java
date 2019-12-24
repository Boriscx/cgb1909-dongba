package com.tedu.controller;

import com.tedu.aspect.annotation.ControllerPermission;
import com.tedu.entity.SysMenu;
import com.tedu.pojo.JsonResult;
import com.tedu.service.SysMenuService;
import com.tedu.util.ShiroUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/menu/")
@Valid
public class SysMenuController {

    private SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @GetMapping("doFindMenusByUserId")
    public JsonResult doFindMenusByUserId() {
        return sysMenuService.findMenusByUserId(ShiroUtil.getUserId());
    }

    @GetMapping("doFindObjects")
    public JsonResult<List> doFindObjects() {
        return new JsonResult<>(sysMenuService.findObjects());
    }

    @GetMapping("doFindNoteObjects")
    public JsonResult<List> doFindNoteObjects() {
        return new JsonResult<>(sysMenuService.findNoteObjects());
    }

    @ControllerPermission("sys:menu:update")
    @PostMapping("doUpdateObject")
    public JsonResult doUpdateObject(@NotNull SysMenu sysMenu) {
        sysMenuService.updateObject(sysMenu);
        return JsonResult.success("更新菜单成功");
    }

    @ControllerPermission("sys:menu:add")
    @PostMapping("doSaveObject")
    public JsonResult doSaveObject(@NotNull SysMenu sysMenu) {
        sysMenuService.saveObject(sysMenu);
        return JsonResult.success("添加菜单成功");
    }

    @ControllerPermission("sys:menu:del")
    @PostMapping("doDeleteObject")
    public JsonResult doDeleteObject(@NotNull @Min(1) Integer id) {
        sysMenuService.deleteById(id);
        return JsonResult.success("删除成功");
    }


}
