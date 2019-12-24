package com.tedu.controller;

import com.tedu.entity.SysRole;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;
import com.tedu.service.SysRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/role/")
public class SysRoleController {

    private SysRoleService sysRoleService;

    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @RequestMapping("doFindObjects")
    public JsonResult<?> doFindObjects() {
        return sysRoleService.findObjects();
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObject(String username,@NotNull @Min(1) Integer pageCurrent, Integer pageSize) {
        return sysRoleService.findPageObjects(username, pageCurrent, pageSize);
    }

    @GetMapping("doFindObjectById")
    public JsonResult doFindObjectById(@NotNull @Min(1) Integer id) {
        return sysRoleService.findObjectById(id);
    }

    @PostMapping("doDeleteObject")
    public JsonResult doDeleteById(@NotNull @Min(1) Integer id) {
        return sysRoleService.deleteById(id);
    }

    @PostMapping("doUpdateObject")
    public JsonResult doUpdateObject(@NotNull SysRole sysRole) {
        return sysRoleService.updateObject(sysRole);
    }

    @PostMapping("doSaveObject")
    public JsonResult doSaveObject(@NotNull SysRole sysRole) {
        return sysRoleService.saveObject(sysRole);
    }

}
