package com.tedu.controller;

import com.tedu.aspect.annotation.ControllerPermission;
import com.tedu.entity.SysDept;
import com.tedu.entity.SysMenu;
import com.tedu.pojo.JsonResult;
import com.tedu.service.SysDeptService;
import com.tedu.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {

    private SysDeptService sysDeptService;

    public SysDeptController(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }


    @GetMapping("doFindObjects")
    public JsonResult doFindObjects() {
        return sysDeptService.findObjects() ;
    }

//    @RequiresPermissions("sys:dept:view")
    @ControllerPermission("sys:dept:view")
    @GetMapping("doFindNoteObjects")
    public JsonResult doFindNoteObjects(){
        return sysDeptService.findNoteObjects();
    }

    // 注解权限控制
    @ControllerPermission("sys:dept:update")
    @PostMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysDept sysDept) {
        sysDeptService.updateObject(sysDept);
        return JsonResult.success("更新部门成功");
    }

    @ControllerPermission("sys:dept:save")
    @PostMapping("doSaveObject")
    public JsonResult doSaveObject(@Valid SysDept sysDept){
        sysDeptService.saveObject(sysDept);
        return JsonResult.success("添加部门成功");
    }

    @PostMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysDeptService.deleteById(id);
        return JsonResult.success("删除部门成功");
    }




}
