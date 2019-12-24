package com.tedu.controller;

import com.tedu.pojo.JsonResult;
import com.tedu.service.SysUserService;
import com.tedu.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RequestMapping("user")
@RestController
public class SysUserController {

    private SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username, Integer pageCurrent, Integer pageSize) {
        return sysUserService.findPageObjects(username, pageCurrent, pageSize);
    }

    @PostMapping("doLogin")
    public JsonResult doLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return JsonResult.success("登录成功!");
    }

    @DeleteMapping("doDeleteObject")
    public JsonResult doDelete(Integer id){
        return sysUserService.deleteObjectById(id);
    }

    @RequestMapping("doLogout")
    public JsonResult doLogout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.success("退出成功");
    }

    @RequestMapping("getUsername")
    public JsonResult getUserName(){
        return new JsonResult<>(ShiroUtil.getUsername());
    }

}
