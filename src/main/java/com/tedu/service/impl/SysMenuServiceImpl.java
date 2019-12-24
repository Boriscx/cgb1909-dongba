package com.tedu.service.impl;

import com.tedu.aspect.annotation.RequestLog;
import com.tedu.dao.SysMenuDao;
import com.tedu.entity.SysMenu;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.MenuNote;
import com.tedu.pojo.UserMenuVo;
import com.tedu.service.SysMenuService;
import com.tedu.util.Assert;
import com.tedu.util.ShiroUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuDao sysMenuDao;

    public SysMenuServiceImpl(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    @Override
    public JsonResult findMenusByUserId(Integer userId) {
        if (userId == null || userId < 1) return JsonResult.error("id值非法");
        return new JsonResult<>(sysMenuDao.findMenusByUserId(userId));
    }

    @RequestLog("查看所有菜单")
    @Override
    public List<SysMenu> findObjects() {
        return sysMenuDao.findObjects();
    }

    @Override
    public List<MenuNote> findNoteObjects() {
        return sysMenuDao.findNoteObjects();
    }

    @RequestLog("修改菜单")
    @Override
    public JsonResult updateObject(SysMenu sysMenu) {
        sysMenu.setModifiedUser(ShiroUtil.getUsername());
        int row = sysMenuDao.updateObject(sysMenu);
        if (row < 1) return JsonResult.error("修改失败");
        return JsonResult.success("修改成功");
    }

    @RequestLog("添加菜单")
    @Override
    public void saveObject(SysMenu sysMenu) {
//        Assert.isNull(sysMenu, "更新数据不能为空");
        sysMenu.setCreatedUser(ShiroUtil.getUsername());
        int row = sysMenuDao.insertObject(sysMenu);
        Assert.isValid(row < 1, "添加部门失败");
    }

    @RequestLog("删除菜单")
    @Override
    public void deleteById(Integer id) {
        Assert.isValid(id == null || id < 1, "ID值非法");
        int row = sysMenuDao.deleteById(id);
        Assert.isValid(row < 1, "删除部门失败");
    }
}
